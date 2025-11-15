package org.springframework.boot.loader.jar;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ValueRange;
import org.springframework.boot.loader.data.RandomAccessData;

final class CentralDirectoryFileHeader implements FileHeader {
   private static final AsciiBytes SLASH = new AsciiBytes("/");
   private static final byte[] NO_EXTRA = new byte[0];
   private static final AsciiBytes NO_COMMENT = new AsciiBytes("");
   private byte[] header;
   private int headerOffset;
   private AsciiBytes name;
   private byte[] extra;
   private AsciiBytes comment;
   private long localHeaderOffset;

   CentralDirectoryFileHeader() {
   }

   CentralDirectoryFileHeader(byte[] header, int headerOffset, AsciiBytes name, byte[] extra, AsciiBytes comment, long localHeaderOffset) {
      this.header = header;
      this.headerOffset = headerOffset;
      this.name = name;
      this.extra = extra;
      this.comment = comment;
      this.localHeaderOffset = localHeaderOffset;
   }

   void load(byte[] data, int dataOffset, RandomAccessData variableData, long variableOffset, JarEntryFilter filter) throws IOException {
      this.header = data;
      this.headerOffset = dataOffset;
      long compressedSize = Bytes.littleEndianValue(data, dataOffset + 20, 4);
      long uncompressedSize = Bytes.littleEndianValue(data, dataOffset + 24, 4);
      long nameLength = Bytes.littleEndianValue(data, dataOffset + 28, 2);
      long extraLength = Bytes.littleEndianValue(data, dataOffset + 30, 2);
      long commentLength = Bytes.littleEndianValue(data, dataOffset + 32, 2);
      long localHeaderOffset = Bytes.littleEndianValue(data, dataOffset + 42, 4);
      dataOffset += 46;
      if (variableData != null) {
         data = variableData.read(variableOffset + 46L, nameLength + extraLength + commentLength);
         dataOffset = 0;
      }

      this.name = new AsciiBytes(data, dataOffset, (int)nameLength);
      if (filter != null) {
         this.name = filter.apply(this.name);
      }

      this.extra = NO_EXTRA;
      this.comment = NO_COMMENT;
      if (extraLength > 0L) {
         this.extra = new byte[(int)extraLength];
         System.arraycopy(data, (int)((long)dataOffset + nameLength), this.extra, 0, this.extra.length);
      }

      this.localHeaderOffset = this.getLocalHeaderOffset(compressedSize, uncompressedSize, localHeaderOffset, this.extra);
      if (commentLength > 0L) {
         this.comment = new AsciiBytes(data, (int)((long)dataOffset + nameLength + extraLength), (int)commentLength);
      }

   }

   private long getLocalHeaderOffset(long compressedSize, long uncompressedSize, long localHeaderOffset, byte[] extra) throws IOException {
      if (localHeaderOffset != 4294967295L) {
         return localHeaderOffset;
      } else {
         int length;
         for(int extraOffset = 0; extraOffset < extra.length - 2; extraOffset += length) {
            int id = (int)Bytes.littleEndianValue(extra, extraOffset, 2);
            length = (int)Bytes.littleEndianValue(extra, extraOffset, 2);
            extraOffset += 4;
            if (id == 1) {
               int localHeaderExtraOffset = 0;
               if (compressedSize == 4294967295L) {
                  localHeaderExtraOffset += 4;
               }

               if (uncompressedSize == 4294967295L) {
                  localHeaderExtraOffset += 4;
               }

               return Bytes.littleEndianValue(extra, extraOffset + localHeaderExtraOffset, 8);
            }
         }

         throw new IOException("Zip64 Extended Information Extra Field not found");
      }
   }

   AsciiBytes getName() {
      return this.name;
   }

   public boolean hasName(CharSequence name, char suffix) {
      return this.name.matches(name, suffix);
   }

   boolean isDirectory() {
      return this.name.endsWith(SLASH);
   }

   public int getMethod() {
      return (int)Bytes.littleEndianValue(this.header, this.headerOffset + 10, 2);
   }

   long getTime() {
      long datetime = Bytes.littleEndianValue(this.header, this.headerOffset + 12, 4);
      return this.decodeMsDosFormatDateTime(datetime);
   }

   private long decodeMsDosFormatDateTime(long datetime) {
      int year = getChronoValue((datetime >> 25 & 127L) + 1980L, ChronoField.YEAR);
      int month = getChronoValue(datetime >> 21 & 15L, ChronoField.MONTH_OF_YEAR);
      int day = getChronoValue(datetime >> 16 & 31L, ChronoField.DAY_OF_MONTH);
      int hour = getChronoValue(datetime >> 11 & 31L, ChronoField.HOUR_OF_DAY);
      int minute = getChronoValue(datetime >> 5 & 63L, ChronoField.MINUTE_OF_HOUR);
      int second = getChronoValue(datetime << 1 & 62L, ChronoField.SECOND_OF_MINUTE);
      return ZonedDateTime.of(year, month, day, hour, minute, second, 0, ZoneId.systemDefault()).toInstant().truncatedTo(ChronoUnit.SECONDS).toEpochMilli();
   }

   long getCrc() {
      return Bytes.littleEndianValue(this.header, this.headerOffset + 16, 4);
   }

   public long getCompressedSize() {
      return Bytes.littleEndianValue(this.header, this.headerOffset + 20, 4);
   }

   public long getSize() {
      return Bytes.littleEndianValue(this.header, this.headerOffset + 24, 4);
   }

   byte[] getExtra() {
      return this.extra;
   }

   boolean hasExtra() {
      return this.extra.length > 0;
   }

   AsciiBytes getComment() {
      return this.comment;
   }

   public long getLocalHeaderOffset() {
      return this.localHeaderOffset;
   }

   public CentralDirectoryFileHeader clone() {
      byte[] header = new byte[46];
      System.arraycopy(this.header, this.headerOffset, header, 0, header.length);
      return new CentralDirectoryFileHeader(header, 0, this.name, header, this.comment, this.localHeaderOffset);
   }

   static CentralDirectoryFileHeader fromRandomAccessData(RandomAccessData data, long offset, JarEntryFilter filter) throws IOException {
      CentralDirectoryFileHeader fileHeader = new CentralDirectoryFileHeader();
      byte[] bytes = data.read(offset, 46L);
      fileHeader.load(bytes, 0, data, offset, filter);
      return fileHeader;
   }

   private static int getChronoValue(long value, ChronoField field) {
      ValueRange range = field.range();
      return Math.toIntExact(Math.min(Math.max(value, range.getMinimum()), range.getMaximum()));
   }
}
