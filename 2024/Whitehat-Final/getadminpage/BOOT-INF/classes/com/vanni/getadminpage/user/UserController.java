package com.vanni.getadminpage.user;

import com.vanni.getadminpage.user.dto.UserEntity;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"fsalke2j9sdfcjlz"})
@Controller
public class UserController {
   private final UserService userService;

   @Autowired
   public UserController(UserService userService) {
      this.userService = userService;
   }

   @PutMapping({"/MbyMx6EtyTm04EyJezkDTEPDipepro"})
   public ResponseEntity<Map<String, Boolean>> signup(@RequestBody UserEntity user) {
      boolean ret = this.userService.insertUser(user);
      Map<String, Boolean> response = new HashMap();
      if (!ret) {
         response.put("status", false);
      } else {
         response.put("status", true);
      }

      return ResponseEntity.ok(response);
   }

   @PutMapping({"/sMo98RyFqT6aKOmcF2NqarIpmrz2ZV"})
   public ResponseEntity<Map<String, Boolean>> login(@RequestBody UserEntity user, Model model, HttpServletRequest req) {
      HttpSession session = req.getSession(true);
      HashMap response = new HashMap();

      try {
         UserEntity data = this.userService.LoginUser(user);
         if (data != null) {
            session.setAttribute("ID", data.getId());
            session.setAttribute("Username", data.getUsername());
            session.setAttribute("Role", data.getUserinfo() != null ? data.getUserinfo().getRole() : null);
            response.put("status", true);
            return ResponseEntity.ok(response);
         } else {
            response.put("status", false);
            return ResponseEntity.ok(response);
         }
      } catch (Exception var7) {
         response.put("status", false);
         return ResponseEntity.ok(response);
      }
   }

   @PutMapping({"/ktAIeS2LZ1p580r84cTrgo4fkD3Pue"})
   public ResponseEntity<?> userinfo(HttpServletRequest req, Model model) {
      HttpSession session = req.getSession(false);
      if (session != null) {
         Long Id = (Long)session.getAttribute("ID");
         UserEntity data = this.userService.selectUser(Id);
         return ResponseEntity.ok(data);
      } else {
         Map<String, Boolean> response = new HashMap();
         response.put("status", false);
         return ResponseEntity.ok(response);
      }
   }

   @PutMapping({"/RAJcUsUYqKShqwBv5w39mnoRXIMlGV"})
   public ResponseEntity<?> userUpdate(@RequestBody UserEntity user, HttpServletRequest req) {
      HashMap response = new HashMap();

      UserEntity data;
      try {
         HttpSession session = req.getSession(false);
         Long Id = (Long)session.getAttribute("ID");
         data = this.userService.updateUser(Id, user);
      } catch (Exception var7) {
         response.put("status", false);
         return ResponseEntity.ok(response);
      }

      return ResponseEntity.ok(data);
   }

   @PutMapping({"/OizzlAXKlJeCigyimwvd8FvYQWmayY"})
   public ResponseEntity<?> userdelete(HttpServletRequest req, Model model) {
      HttpSession session = req.getSession(false);
      HashMap response = new HashMap();

      try {
         if (session == null) {
            response.put("status", false);
            return ResponseEntity.ok(response);
         }

         Long Id = (Long)session.getAttribute("ID");
         this.userService.deleteUser(Id);
      } catch (Exception var6) {
         response.put("status", false);
         return ResponseEntity.ok(response);
      }

      response.put("status", true);
      return ResponseEntity.ok(response);
   }
}
