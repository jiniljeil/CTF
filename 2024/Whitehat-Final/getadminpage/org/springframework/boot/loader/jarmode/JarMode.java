package org.springframework.boot.loader.jarmode;

public interface JarMode {
   boolean accepts(String mode);

   void run(String mode, String[] args);
}
