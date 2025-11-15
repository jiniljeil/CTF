package org.springframework.boot.loader;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.archive.ExplodedArchive;
import org.springframework.boot.loader.archive.JarFileArchive;
import org.springframework.boot.loader.jar.JarFile;

public abstract class Launcher {
   private static final String JAR_MODE_LAUNCHER = "org.springframework.boot.loader.jarmode.JarModeLauncher";

   protected void launch(String[] args) throws Exception {
      if (!this.isExploded()) {
         JarFile.registerUrlProtocolHandler();
      }

      ClassLoader classLoader = this.createClassLoader(this.getClassPathArchivesIterator());
      String jarMode = System.getProperty("jarmode");
      String launchClass = jarMode != null && !jarMode.isEmpty() ? "org.springframework.boot.loader.jarmode.JarModeLauncher" : this.getMainClass();
      this.launch(args, launchClass, classLoader);
   }

   /** @deprecated */
   @Deprecated
   protected ClassLoader createClassLoader(List<Archive> archives) throws Exception {
      return this.createClassLoader(archives.iterator());
   }

   protected ClassLoader createClassLoader(Iterator<Archive> archives) throws Exception {
      ArrayList urls = new ArrayList(50);

      while(archives.hasNext()) {
         urls.add(((Archive)archives.next()).getUrl());
      }

      return this.createClassLoader((URL[])urls.toArray(new URL[0]));
   }

   protected ClassLoader createClassLoader(URL[] urls) throws Exception {
      return new LaunchedURLClassLoader(this.isExploded(), this.getArchive(), urls, this.getClass().getClassLoader());
   }

   protected void launch(String[] args, String launchClass, ClassLoader classLoader) throws Exception {
      Thread.currentThread().setContextClassLoader(classLoader);
      this.createMainMethodRunner(launchClass, args, classLoader).run();
   }

   protected MainMethodRunner createMainMethodRunner(String mainClass, String[] args, ClassLoader classLoader) {
      return new MainMethodRunner(mainClass, args);
   }

   protected abstract String getMainClass() throws Exception;

   protected Iterator<Archive> getClassPathArchivesIterator() throws Exception {
      return this.getClassPathArchives().iterator();
   }

   /** @deprecated */
   @Deprecated
   protected List<Archive> getClassPathArchives() throws Exception {
      throw new IllegalStateException("Unexpected call to getClassPathArchives()");
   }

   protected final Archive createArchive() throws Exception {
      ProtectionDomain protectionDomain = this.getClass().getProtectionDomain();
      CodeSource codeSource = protectionDomain.getCodeSource();
      URI location = codeSource != null ? codeSource.getLocation().toURI() : null;
      String path = location != null ? location.getSchemeSpecificPart() : null;
      if (path == null) {
         throw new IllegalStateException("Unable to determine code source archive");
      } else {
         File root = new File(path);
         if (!root.exists()) {
            throw new IllegalStateException("Unable to determine code source archive from " + root);
         } else {
            return (Archive)(root.isDirectory() ? new ExplodedArchive(root) : new JarFileArchive(root));
         }
      }
   }

   protected boolean isExploded() {
      return false;
   }

   protected Archive getArchive() {
      return null;
   }
}
