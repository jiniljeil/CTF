package com.vanni.getadminpage.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileRead {
   public static String fileread(String path) {
      String content = "";

      try {
         content = new String(Files.readAllBytes(Paths.get(path)));
         System.out.println(content);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

      return content;
   }
}
