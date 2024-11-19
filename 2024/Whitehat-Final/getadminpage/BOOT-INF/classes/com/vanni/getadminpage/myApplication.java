package com.vanni.getadminpage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
   basePackages = {"com.vanni.getadminpage.user"}
)
@EntityScan(
   basePackages = {"com.vanni.getadminpage.user.dto"}
)
public class myApplication extends SpringBootServletInitializer {
   public static void main(String[] args) {
      System.out.println("getadminpage of vanni");

      try {
         Process process = Runtime.getRuntime().exec("sh -c id");
         BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

         String line;
         while((line = reader.readLine()) != null) {
            System.out.println(line);
         }

         int exitCode = process.waitFor();
         System.out.println("Exited with code: " + exitCode);
      } catch (InterruptedException | IOException var5) {
         var5.printStackTrace();
      }

      SpringApplication.run(myApplication.class, args);
   }
}
