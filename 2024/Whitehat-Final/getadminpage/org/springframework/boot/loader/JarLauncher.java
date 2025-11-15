package org.springframework.boot.loader;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.ExplodedArchive;

public class JarLauncher extends ExecutableArchiveLauncher {
   private static final String DEFAULT_CLASSPATH_INDEX_LOCATION = "BOOT-INF/classpath.idx";
   static final Archive.EntryFilter NESTED_ARCHIVE_ENTRY_FILTER = (entry) -> {
      return entry.isDirectory() ? entry.getName().equals("BOOT-INF/classes/") : entry.getName().startsWith("BOOT-INF/lib/");
   };

   public JarLauncher() {
   }

   protected JarLauncher(Archive archive) {
      super(archive);
   }

   protected ClassPathIndexFile getClassPathIndex(Archive archive) throws IOException {
      if (archive instanceof ExplodedArchive) {
         String location = this.getClassPathIndexFileLocation(archive);
         return ClassPathIndexFile.loadIfPossible(archive.getUrl(), location);
      } else {
         return super.getClassPathIndex(archive);
      }
   }

   private String getClassPathIndexFileLocation(Archive archive) throws IOException {
      Manifest manifest = archive.getManifest();
      Attributes attributes = manifest != null ? manifest.getMainAttributes() : null;
      String location = attributes != null ? attributes.getValue("Spring-Boot-Classpath-Index") : null;
      return location != null ? location : "BOOT-INF/classpath.idx";
   }

   protected boolean isPostProcessingClassPathArchives() {
      return false;
   }

   protected boolean isSearchCandidate(Archive.Entry entry) {
      return entry.getName().startsWith("BOOT-INF/");
   }

   protected boolean isNestedArchive(Archive.Entry entry) {
      return NESTED_ARCHIVE_ENTRY_FILTER.matches(entry);
   }

   public static void main(String[] args) throws Exception {
      (new JarLauncher()).launch(args);
   }
}
