package org.springframework.boot.loader.jar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Permission;
import java.util.Enumeration;
import java.util.jar.Manifest;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

class JarFileWrapper extends AbstractJarFile {
   private final JarFile parent;

   JarFileWrapper(JarFile parent) throws IOException {
      super(parent.getRootJarFile().getFile());
      this.parent = parent;
      if (System.getSecurityManager() == null) {
         super.close();
      }

   }

   URL getUrl() throws MalformedURLException {
      return this.parent.getUrl();
   }

   AbstractJarFile.JarFileType getType() {
      return this.parent.getType();
   }

   Permission getPermission() {
      return this.parent.getPermission();
   }

   public Manifest getManifest() throws IOException {
      return this.parent.getManifest();
   }

   public Enumeration<java.util.jar.JarEntry> entries() {
      return this.parent.entries();
   }

   public Stream<java.util.jar.JarEntry> stream() {
      return this.parent.stream();
   }

   public java.util.jar.JarEntry getJarEntry(String name) {
      return this.parent.getJarEntry(name);
   }

   public ZipEntry getEntry(String name) {
      return this.parent.getEntry(name);
   }

   InputStream getInputStream() throws IOException {
      return this.parent.getInputStream();
   }

   public synchronized InputStream getInputStream(ZipEntry ze) throws IOException {
      return this.parent.getInputStream(ze);
   }

   public String getComment() {
      return this.parent.getComment();
   }

   public int size() {
      return this.parent.size();
   }

   public String toString() {
      return this.parent.toString();
   }

   public String getName() {
      return this.parent.getName();
   }

   static JarFile unwrap(java.util.jar.JarFile jarFile) {
      if (jarFile instanceof JarFile) {
         return (JarFile)jarFile;
      } else if (jarFile instanceof JarFileWrapper) {
         return unwrap(((JarFileWrapper)jarFile).parent);
      } else {
         throw new IllegalStateException("Not a JarFile or Wrapper");
      }
   }
}
