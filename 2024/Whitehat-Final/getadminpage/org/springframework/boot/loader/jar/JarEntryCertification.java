package org.springframework.boot.loader.jar;

import java.security.CodeSigner;
import java.security.cert.Certificate;

class JarEntryCertification {
   static final JarEntryCertification NONE = new JarEntryCertification((Certificate[])null, (CodeSigner[])null);
   private final Certificate[] certificates;
   private final CodeSigner[] codeSigners;

   JarEntryCertification(Certificate[] certificates, CodeSigner[] codeSigners) {
      this.certificates = certificates;
      this.codeSigners = codeSigners;
   }

   Certificate[] getCertificates() {
      return this.certificates != null ? (Certificate[])this.certificates.clone() : null;
   }

   CodeSigner[] getCodeSigners() {
      return this.codeSigners != null ? (CodeSigner[])this.codeSigners.clone() : null;
   }

   static JarEntryCertification from(java.util.jar.JarEntry certifiedEntry) {
      Certificate[] certificates = certifiedEntry != null ? certifiedEntry.getCertificates() : null;
      CodeSigner[] codeSigners = certifiedEntry != null ? certifiedEntry.getCodeSigners() : null;
      return certificates == null && codeSigners == null ? NONE : new JarEntryCertification(certificates, codeSigners);
   }
}
