package org.springframework.boot.loader;

import org.springframework.boot.loader.archive.Archive;

public class WarLauncher extends ExecutableArchiveLauncher {
   public WarLauncher() {
   }

   protected WarLauncher(Archive archive) {
      super(archive);
   }

   protected boolean isPostProcessingClassPathArchives() {
      return false;
   }

   protected boolean isSearchCandidate(Archive.Entry entry) {
      return entry.getName().startsWith("WEB-INF/");
   }

   public boolean isNestedArchive(Archive.Entry entry) {
      if (entry.isDirectory()) {
         return entry.getName().equals("WEB-INF/classes/");
      } else {
         return entry.getName().startsWith("WEB-INF/lib/") || entry.getName().startsWith("WEB-INF/lib-provided/");
      }
   }

   public static void main(String[] args) throws Exception {
      (new WarLauncher()).launch(args);
   }
}
