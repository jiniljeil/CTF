package org.springframework.boot.loader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.Enumeration;
import java.util.function.Supplier;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import org.springframework.boot.loader.archive.Archive;
import org.springframework.boot.loader.jar.Handler;

public class LaunchedURLClassLoader extends URLClassLoader {
   private static final int BUFFER_SIZE = 4096;
   private final boolean exploded;
   private final Archive rootArchive;
   private final Object packageLock;
   private volatile LaunchedURLClassLoader.DefinePackageCallType definePackageCallType;

   public LaunchedURLClassLoader(URL[] urls, ClassLoader parent) {
      this(false, urls, parent);
   }

   public LaunchedURLClassLoader(boolean exploded, URL[] urls, ClassLoader parent) {
      this(exploded, (Archive)null, urls, parent);
   }

   public LaunchedURLClassLoader(boolean exploded, Archive rootArchive, URL[] urls, ClassLoader parent) {
      super(urls, parent);
      this.packageLock = new Object();
      this.exploded = exploded;
      this.rootArchive = rootArchive;
   }

   public URL findResource(String name) {
      if (this.exploded) {
         return super.findResource(name);
      } else {
         Handler.setUseFastConnectionExceptions(true);

         URL var2;
         try {
            var2 = super.findResource(name);
         } finally {
            Handler.setUseFastConnectionExceptions(false);
         }

         return var2;
      }
   }

   public Enumeration<URL> findResources(String name) throws IOException {
      if (this.exploded) {
         return super.findResources(name);
      } else {
         Handler.setUseFastConnectionExceptions(true);

         LaunchedURLClassLoader.UseFastConnectionExceptionsEnumeration var2;
         try {
            var2 = new LaunchedURLClassLoader.UseFastConnectionExceptionsEnumeration(super.findResources(name));
         } finally {
            Handler.setUseFastConnectionExceptions(false);
         }

         return var2;
      }
   }

   protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
      Class result;
      if (name.startsWith("org.springframework.boot.loader.jarmode.")) {
         try {
            result = this.loadClassInLaunchedClassLoader(name);
            if (resolve) {
               this.resolveClass(result);
            }

            return result;
         } catch (ClassNotFoundException var10) {
         }
      }

      if (this.exploded) {
         return super.loadClass(name, resolve);
      } else {
         Handler.setUseFastConnectionExceptions(true);

         try {
            try {
               this.definePackageIfNecessary(name);
            } catch (IllegalArgumentException var8) {
               if (this.getPackage(name) == null) {
                  throw new AssertionError("Package " + name + " has already been defined but it could not be found");
               }
            }

            result = super.loadClass(name, resolve);
         } finally {
            Handler.setUseFastConnectionExceptions(false);
         }

         return result;
      }
   }

   private Class<?> loadClassInLaunchedClassLoader(String name) throws ClassNotFoundException {
      String internalName = name.replace('.', '/') + ".class";
      InputStream inputStream = this.getParent().getResourceAsStream(internalName);
      if (inputStream == null) {
         throw new ClassNotFoundException(name);
      } else {
         try {
            try {
               ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
               byte[] buffer = new byte[4096];
               boolean var6 = true;

               int bytesRead;
               while((bytesRead = inputStream.read(buffer)) != -1) {
                  outputStream.write(buffer, 0, bytesRead);
               }

               inputStream.close();
               byte[] bytes = outputStream.toByteArray();
               Class<?> definedClass = this.defineClass(name, bytes, 0, bytes.length);
               this.definePackageIfNecessary(name);
               Class var9 = definedClass;
               return var9;
            } finally {
               inputStream.close();
            }
         } catch (IOException var14) {
            throw new ClassNotFoundException("Cannot load resource for class [" + name + "]", var14);
         }
      }
   }

   private void definePackageIfNecessary(String className) {
      int lastDot = className.lastIndexOf(46);
      if (lastDot >= 0) {
         String packageName = className.substring(0, lastDot);
         if (this.getPackage(packageName) == null) {
            try {
               this.definePackage(className, packageName);
            } catch (IllegalArgumentException var5) {
               if (this.getPackage(packageName) == null) {
                  throw new AssertionError("Package " + packageName + " has already been defined but it could not be found");
               }
            }
         }
      }

   }

   private void definePackage(String className, String packageName) {
      try {
         AccessController.doPrivileged(() -> {
            String packageEntryName = packageName.replace('.', '/') + "/";
            String classEntryName = className.replace('.', '/') + ".class";
            URL[] var5 = this.getURLs();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
               URL url = var5[var7];

               try {
                  URLConnection connection = url.openConnection();
                  if (connection instanceof JarURLConnection) {
                     JarFile jarFile = ((JarURLConnection)connection).getJarFile();
                     if (jarFile.getEntry(classEntryName) != null && jarFile.getEntry(packageEntryName) != null && jarFile.getManifest() != null) {
                        this.definePackage(packageName, jarFile.getManifest(), url);
                        return null;
                     }
                  }
               } catch (IOException var11) {
               }
            }

            return null;
         }, AccessController.getContext());
      } catch (PrivilegedActionException var4) {
      }

   }

   protected Package definePackage(String name, Manifest man, URL url) throws IllegalArgumentException {
      if (!this.exploded) {
         return super.definePackage(name, man, url);
      } else {
         synchronized(this.packageLock) {
            return (Package)this.doDefinePackage(LaunchedURLClassLoader.DefinePackageCallType.MANIFEST, () -> {
               return super.definePackage(name, man, url);
            });
         }
      }
   }

   protected Package definePackage(String name, String specTitle, String specVersion, String specVendor, String implTitle, String implVersion, String implVendor, URL sealBase) throws IllegalArgumentException {
      if (!this.exploded) {
         return super.definePackage(name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, sealBase);
      } else {
         synchronized(this.packageLock) {
            if (this.definePackageCallType == null) {
               Manifest manifest = this.getManifest(this.rootArchive);
               if (manifest != null) {
                  return this.definePackage(name, manifest, sealBase);
               }
            }

            return (Package)this.doDefinePackage(LaunchedURLClassLoader.DefinePackageCallType.ATTRIBUTES, () -> {
               return super.definePackage(name, specTitle, specVersion, specVendor, implTitle, implVersion, implVendor, sealBase);
            });
         }
      }
   }

   private Manifest getManifest(Archive archive) {
      try {
         return archive != null ? archive.getManifest() : null;
      } catch (IOException var3) {
         return null;
      }
   }

   private <T> T doDefinePackage(LaunchedURLClassLoader.DefinePackageCallType type, Supplier<T> call) {
      LaunchedURLClassLoader.DefinePackageCallType existingType = this.definePackageCallType;

      Object var4;
      try {
         this.definePackageCallType = type;
         var4 = call.get();
      } finally {
         this.definePackageCallType = existingType;
      }

      return var4;
   }

   public void clearCache() {
      if (!this.exploded) {
         URL[] var1 = this.getURLs();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            URL url = var1[var3];

            try {
               URLConnection connection = url.openConnection();
               if (connection instanceof JarURLConnection) {
                  this.clearCache(connection);
               }
            } catch (IOException var6) {
            }
         }

      }
   }

   private void clearCache(URLConnection connection) throws IOException {
      Object jarFile = ((JarURLConnection)connection).getJarFile();
      if (jarFile instanceof org.springframework.boot.loader.jar.JarFile) {
         ((org.springframework.boot.loader.jar.JarFile)jarFile).clearCache();
      }

   }

   static {
      ClassLoader.registerAsParallelCapable();
   }

   private static enum DefinePackageCallType {
      MANIFEST,
      ATTRIBUTES;
   }

   private static class UseFastConnectionExceptionsEnumeration implements Enumeration<URL> {
      private final Enumeration<URL> delegate;

      UseFastConnectionExceptionsEnumeration(Enumeration<URL> delegate) {
         this.delegate = delegate;
      }

      public boolean hasMoreElements() {
         Handler.setUseFastConnectionExceptions(true);

         boolean var1;
         try {
            var1 = this.delegate.hasMoreElements();
         } finally {
            Handler.setUseFastConnectionExceptions(false);
         }

         return var1;
      }

      public URL nextElement() {
         Handler.setUseFastConnectionExceptions(true);

         URL var1;
         try {
            var1 = (URL)this.delegate.nextElement();
         } finally {
            Handler.setUseFastConnectionExceptions(false);
         }

         return var1;
      }
   }
}
