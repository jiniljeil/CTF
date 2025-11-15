package org.springframework.boot.loader.jarmode;

import java.util.Iterator;
import java.util.List;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

public final class JarModeLauncher {
   static final String DISABLE_SYSTEM_EXIT = JarModeLauncher.class.getName() + ".DISABLE_SYSTEM_EXIT";

   private JarModeLauncher() {
   }

   public static void main(String[] args) {
      String mode = System.getProperty("jarmode");
      List<JarMode> candidates = SpringFactoriesLoader.loadFactories(JarMode.class, ClassUtils.getDefaultClassLoader());
      Iterator var3 = candidates.iterator();

      JarMode candidate;
      do {
         if (!var3.hasNext()) {
            System.err.println("Unsupported jarmode '" + mode + "'");
            if (!Boolean.getBoolean(DISABLE_SYSTEM_EXIT)) {
               System.exit(1);
            }

            return;
         }

         candidate = (JarMode)var3.next();
      } while(!candidate.accepts(mode));

      candidate.run(mode, args);
   }
}
