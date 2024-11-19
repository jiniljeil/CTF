package com.vanni.getadminpage.admin;

import com.vanni.getadminpage.util.FileRead;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"L0jU3lgokNLUQ7W1nppJ"})
@Controller
public class AdminController {
   @PutMapping({"/XXnNWBoD9DWidSaR0aVVFmD8sNeiLz"})
   public ResponseEntity<Map<String, ?>> admin(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Map<String, Object> response = new HashMap();
      String content = "";
      if (session != null) {
         if (session.getAttribute("Role").equals("Admin")) {
            response.put("status", true);
            content = FileRead.fileread("/flag");
            response.put("FLAG", content);
            return ResponseEntity.ok(response);
         }

         response.put("status", false);
      }

      return ResponseEntity.ok(response);
   }
}
