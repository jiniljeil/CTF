package org.springframework.boot.loader.jar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSigner;
import java.security.cert.Certificate;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

class JarEntry extends java.util.jar.JarEntry implements FileHeader {
   private final int index;
   private final AsciiBytes name;
   private final AsciiBytes headerName;
   private final JarFile jarFile;
   private long localHeaderOffset;
   private volatile JarEntryCertification certification;

   JarEntry(JarFile jarFile, int index, CentralDirectoryFileHeader header, AsciiBytes nameAlias) {
      super(nameAlias != null ? nameAlias.toString() : header.getName().toString());
      this.index = index;
      this.name = nameAlias != null ? nameAlias : header.getName();
      this.headerName = header.getName();
      this.jarFile = jarFile;
      this.localHeaderOffset = header.getLocalHeaderOffset();
      this.setCompressedSize(header.getCompressedSize());
      this.setMethod(header.getMethod());
      this.setCrc(header.getCrc());
      this.setComment(header.getComment().toString());
      this.setSize(header.getSize());
      this.setTime(header.getTime());
      if (header.hasExtra()) {
         this.setExtra(header.getExtra());
      }

   }

   int getIndex() {
      return this.index;
   }

   AsciiBytes getAsciiBytesName() {
      return this.name;
   }

   public boolean hasName(CharSequence name, char suffix) {
      return this.headerName.matches(name, suffix);
   }

   URL getUrl() throws MalformedURLException {
      return new URL(this.jarFile.getUrl(), this.getName());
   }

   public Attributes getAttributes() throws IOException {
      Manifest manifest = this.jarFile.getManifest();
      return manifest != null ? manifest.getAttributes(this.getName()) : null;
   }

   public Certificate[] getCertificates() {
      return this.getCertification().getCertificates();
   }

   public CodeSigner[] getCodeSigners() {
      return this.getCertification().getCodeSigners();
   }

   private JarEntryCertification getCertification() {
      if (!this.jarFile.isSigned()) {
         return JarEntryCertification.NONE;
      } else {
         JarEntryCertification certification = this.certification;
         if (certification == null) {
            certification = this.jarFile.getCertification(this);
            this.certification = certification;
         }

         return certification;
      }
   }

   public long getLocalHeaderOffset() {
      return this.localHeaderOffset;
   }
}
