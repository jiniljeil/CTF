package org.springframework.boot.loader.jar;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.jar.Attributes.Name;
import org.springframework.boot.loader.data.RandomAccessData;

class JarFileEntries implements CentralDirectoryVisitor, Iterable<JarEntry> {
   private static final Runnable NO_VALIDATION = () -> {
   };
   private static final String META_INF_PREFIX = "META-INF/";
   private static final Name MULTI_RELEASE = new Name("Multi-Release");
   private static final int BASE_VERSION = 8;
   private static final int RUNTIME_VERSION;
   private static final long LOCAL_FILE_HEADER_SIZE = 30L;
   private static final char SLASH = '/';
   private static final char NO_SUFFIX = '\u0000';
   protected static final int ENTRY_CACHE_SIZE = 25;
   private final JarFile jarFile;
   private final JarEntryFilter filter;
   private RandomAccessData centralDirectoryData;
   private int size;
   private int[] hashCodes;
   private JarFileEntries.Offsets centralDirectoryOffsets;
   private int[] positions;
   private Boolean multiReleaseJar;
   private JarEntryCertification[] certifications;
   private final Map<Integer, FileHeader> entriesCache = Collections.synchronizedMap(new LinkedHashMap<Integer, FileHeader>(16, 0.75F, true) {
      protected boolean removeEldestEntry(Entry<Integer, FileHeader> eldest) {
         return this.size() >= 25;
      }
   });

   JarFileEntries(JarFile jarFile, JarEntryFilter filter) {
      this.jarFile = jarFile;
      this.filter = filter;
      if (RUNTIME_VERSION == 8) {
         this.multiReleaseJar = false;
      }

   }

   public void visitStart(CentralDirectoryEndRecord endRecord, RandomAccessData centralDirectoryData) {
      int maxSize = endRecord.getNumberOfRecords();
      this.centralDirectoryData = centralDirectoryData;
      this.hashCodes = new int[maxSize];
      this.centralDirectoryOffsets = JarFileEntries.Offsets.from(endRecord);
      this.positions = new int[maxSize];
   }

   public void visitFileHeader(CentralDirectoryFileHeader fileHeader, long dataOffset) {
      AsciiBytes name = this.applyFilter(fileHeader.getName());
      if (name != null) {
         this.add(name, dataOffset);
      }

   }

   private void add(AsciiBytes name, long dataOffset) {
      this.hashCodes[this.size] = name.hashCode();
      this.centralDirectoryOffsets.set(this.size, dataOffset);
      this.positions[this.size] = this.size++;
   }

   public void visitEnd() {
      this.sort(0, this.size - 1);
      int[] positions = this.positions;
      this.positions = new int[positions.length];

      for(int i = 0; i < this.size; this.positions[positions[i]] = i++) {
      }

   }

   int getSize() {
      return this.size;
   }

   private void sort(int left, int right) {
      if (left < right) {
         int pivot = this.hashCodes[left + (right - left) / 2];
         int i = left;
         int j = right;

         while(i <= j) {
            while(this.hashCodes[i] < pivot) {
               ++i;
            }

            while(this.hashCodes[j] > pivot) {
               --j;
            }

            if (i <= j) {
               this.swap(i, j);
               ++i;
               --j;
            }
         }

         if (left < j) {
            this.sort(left, j);
         }

         if (right > i) {
            this.sort(i, right);
         }
      }

   }

   private void swap(int i, int j) {
      swap(this.hashCodes, i, j);
      this.centralDirectoryOffsets.swap(i, j);
      swap(this.positions, i, j);
   }

   public Iterator<JarEntry> iterator() {
      return new JarFileEntries.EntryIterator(NO_VALIDATION);
   }

   Iterator<JarEntry> iterator(Runnable validator) {
      return new JarFileEntries.EntryIterator(validator);
   }

   boolean containsEntry(CharSequence name) {
      return this.getEntry(name, FileHeader.class, true) != null;
   }

   JarEntry getEntry(CharSequence name) {
      return (JarEntry)this.getEntry(name, JarEntry.class, true);
   }

   InputStream getInputStream(String name) throws IOException {
      FileHeader entry = this.getEntry(name, FileHeader.class, false);
      return this.getInputStream(entry);
   }

   InputStream getInputStream(FileHeader entry) throws IOException {
      if (entry == null) {
         return null;
      } else {
         InputStream inputStream = this.getEntryData(entry).getInputStream();
         if (entry.getMethod() == 8) {
            inputStream = new ZipInflaterInputStream((InputStream)inputStream, (int)entry.getSize());
         }

         return (InputStream)inputStream;
      }
   }

   RandomAccessData getEntryData(String name) throws IOException {
      FileHeader entry = this.getEntry(name, FileHeader.class, false);
      return entry == null ? null : this.getEntryData(entry);
   }

   private RandomAccessData getEntryData(FileHeader entry) throws IOException {
      RandomAccessData data = this.jarFile.getData();
      byte[] localHeader = data.read(entry.getLocalHeaderOffset(), 30L);
      long nameLength = Bytes.littleEndianValue(localHeader, 26, 2);
      long extraLength = Bytes.littleEndianValue(localHeader, 28, 2);
      return data.getSubsection(entry.getLocalHeaderOffset() + 30L + nameLength + extraLength, entry.getCompressedSize());
   }

   private <T extends FileHeader> T getEntry(CharSequence name, Class<T> type, boolean cacheEntry) {
      T entry = this.doGetEntry(name, type, cacheEntry, (AsciiBytes)null);
      if (!this.isMetaInfEntry(name) && this.isMultiReleaseJar()) {
         int version = RUNTIME_VERSION;

         for(AsciiBytes nameAlias = entry instanceof JarEntry ? ((JarEntry)entry).getAsciiBytesName() : new AsciiBytes(name.toString()); version > 8; --version) {
            T versionedEntry = this.doGetEntry("META-INF/versions/" + version + "/" + name, type, cacheEntry, nameAlias);
            if (versionedEntry != null) {
               return versionedEntry;
            }
         }
      }

      return entry;
   }

   private boolean isMetaInfEntry(CharSequence name) {
      return name.toString().startsWith("META-INF/");
   }

   private boolean isMultiReleaseJar() {
      Boolean multiRelease = this.multiReleaseJar;
      if (multiRelease != null) {
         return multiRelease;
      } else {
         try {
            Manifest manifest = this.jarFile.getManifest();
            if (manifest == null) {
               multiRelease = false;
            } else {
               Attributes attributes = manifest.getMainAttributes();
               multiRelease = attributes.containsKey(MULTI_RELEASE);
            }
         } catch (IOException var4) {
            multiRelease = false;
         }

         this.multiReleaseJar = multiRelease;
         return multiRelease;
      }
   }

   private <T extends FileHeader> T doGetEntry(CharSequence name, Class<T> type, boolean cacheEntry, AsciiBytes nameAlias) {
      int hashCode = AsciiBytes.hashCode(name);
      T entry = this.getEntry(hashCode, name, '\u0000', type, cacheEntry, nameAlias);
      if (entry == null) {
         hashCode = AsciiBytes.hashCode(hashCode, '/');
         entry = this.getEntry(hashCode, name, '/', type, cacheEntry, nameAlias);
      }

      return entry;
   }

   private <T extends FileHeader> T getEntry(int hashCode, CharSequence name, char suffix, Class<T> type, boolean cacheEntry, AsciiBytes nameAlias) {
      for(int index = this.getFirstIndex(hashCode); index >= 0 && index < this.size && this.hashCodes[index] == hashCode; ++index) {
         T entry = this.getEntry(index, type, cacheEntry, nameAlias);
         if (entry.hasName(name, suffix)) {
            return entry;
         }
      }

      return null;
   }

   private <T extends FileHeader> T getEntry(int index, Class<T> type, boolean cacheEntry, AsciiBytes nameAlias) {
      try {
         long offset = this.centralDirectoryOffsets.get(index);
         FileHeader cached = (FileHeader)this.entriesCache.get(index);
         FileHeader entry = cached != null ? cached : CentralDirectoryFileHeader.fromRandomAccessData(this.centralDirectoryData, offset, this.filter);
         if (CentralDirectoryFileHeader.class.equals(entry.getClass()) && type.equals(JarEntry.class)) {
            entry = new JarEntry(this.jarFile, index, (CentralDirectoryFileHeader)entry, nameAlias);
         }

         if (cacheEntry && cached != entry) {
            this.entriesCache.put(index, entry);
         }

         return (FileHeader)entry;
      } catch (IOException var9) {
         throw new IllegalStateException(var9);
      }
   }

   private int getFirstIndex(int hashCode) {
      int index = Arrays.binarySearch(this.hashCodes, 0, this.size, hashCode);
      if (index < 0) {
         return -1;
      } else {
         while(index > 0 && this.hashCodes[index - 1] == hashCode) {
            --index;
         }

         return index;
      }
   }

   void clearCache() {
      this.entriesCache.clear();
   }

   private AsciiBytes applyFilter(AsciiBytes name) {
      return this.filter != null ? this.filter.apply(name) : name;
   }

   JarEntryCertification getCertification(JarEntry entry) throws IOException {
      JarEntryCertification[] certifications = this.certifications;
      if (certifications == null) {
         certifications = new JarEntryCertification[this.size];
         JarInputStream certifiedJarStream = new JarInputStream(this.jarFile.getData().getInputStream());
         Throwable var4 = null;

         try {
            java.util.jar.JarEntry certifiedEntry = null;

            while((certifiedEntry = certifiedJarStream.getNextJarEntry()) != null) {
               certifiedJarStream.closeEntry();
               int index = this.getEntryIndex(certifiedEntry.getName());
               if (index != -1) {
                  certifications[index] = JarEntryCertification.from(certifiedEntry);
               }
            }
         } catch (Throwable var14) {
            var4 = var14;
            throw var14;
         } finally {
            if (certifiedJarStream != null) {
               if (var4 != null) {
                  try {
                     certifiedJarStream.close();
                  } catch (Throwable var13) {
                     var4.addSuppressed(var13);
                  }
               } else {
                  certifiedJarStream.close();
               }
            }

         }

         this.certifications = certifications;
      }

      JarEntryCertification certification = certifications[entry.getIndex()];
      return certification != null ? certification : JarEntryCertification.NONE;
   }

   private int getEntryIndex(CharSequence name) {
      int hashCode = AsciiBytes.hashCode(name);

      for(int index = this.getFirstIndex(hashCode); index >= 0 && index < this.size && this.hashCodes[index] == hashCode; ++index) {
         FileHeader candidate = this.getEntry(index, FileHeader.class, false, (AsciiBytes)null);
         if (candidate.hasName(name, '\u0000')) {
            return index;
         }
      }

      return -1;
   }

   private static void swap(int[] array, int i, int j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
   }

   private static void swap(long[] array, int i, int j) {
      long temp = array[i];
      array[i] = array[j];
      array[j] = temp;
   }

   static {
      int version;
      try {
         Object runtimeVersion = Runtime.class.getMethod("version").invoke((Object)null);
         version = (Integer)runtimeVersion.getClass().getMethod("major").invoke(runtimeVersion);
      } catch (Throwable var2) {
         version = 8;
      }

      RUNTIME_VERSION = version;
   }

   private static final class Zip64Offsets implements JarFileEntries.Offsets {
      private final long[] offsets;

      private Zip64Offsets(int size) {
         this.offsets = new long[size];
      }

      public void swap(int i, int j) {
         JarFileEntries.swap(this.offsets, i, j);
      }

      public void set(int index, long value) {
         this.offsets[index] = value;
      }

      public long get(int index) {
         return this.offsets[index];
      }

      // $FF: synthetic method
      Zip64Offsets(int x0, Object x1) {
         this(x0);
      }
   }

   private static final class ZipOffsets implements JarFileEntries.Offsets {
      private final int[] offsets;

      private ZipOffsets(int size) {
         this.offsets = new int[size];
      }

      public void swap(int i, int j) {
         JarFileEntries.swap(this.offsets, i, j);
      }

      public void set(int index, long value) {
         this.offsets[index] = (int)value;
      }

      public long get(int index) {
         return (long)this.offsets[index];
      }

      // $FF: synthetic method
      ZipOffsets(int x0, Object x1) {
         this(x0);
      }
   }

   private interface Offsets {
      void set(int index, long value);

      long get(int index);

      void swap(int i, int j);

      static JarFileEntries.Offsets from(CentralDirectoryEndRecord endRecord) {
         int size = endRecord.getNumberOfRecords();
         return (JarFileEntries.Offsets)(endRecord.isZip64() ? new JarFileEntries.Zip64Offsets(size) : new JarFileEntries.ZipOffsets(size));
      }
   }

   private final class EntryIterator implements Iterator<JarEntry> {
      private final Runnable validator;
      private int index;

      private EntryIterator(Runnable validator) {
         this.index = 0;
         this.validator = validator;
         validator.run();
      }

      public boolean hasNext() {
         this.validator.run();
         return this.index < JarFileEntries.this.size;
      }

      public JarEntry next() {
         this.validator.run();
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            int entryIndex = JarFileEntries.this.positions[this.index];
            ++this.index;
            return (JarEntry)JarFileEntries.this.getEntry(entryIndex, JarEntry.class, false, (AsciiBytes)null);
         }
      }

      // $FF: synthetic method
      EntryIterator(Runnable x1, Object x2) {
         this(x1);
      }
   }
}
