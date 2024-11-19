package org.springframework.boot.loader;

import java.lang.reflect.Method;

public class MainMethodRunner {
   private final String mainClassName;
   private final String[] args;

   public MainMethodRunner(String mainClass, String[] args) {
      this.mainClassName = mainClass;
      this.args = args != null ? (String[])args.clone() : null;
   }

   public void run() throws Exception {
      Class<?> mainClass = Class.forName(this.mainClassName, false, Thread.currentThread().getContextClassLoader());
      Method mainMethod = mainClass.getDeclaredMethod("main", String[].class);
      mainMethod.setAccessible(true);
      mainMethod.invoke((Object)null, this.args);
   }
}
