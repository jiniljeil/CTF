package com.vanni.getadminpage.user.dto;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserInfoEntity {
   @Column
   private String Role = "ROLE_USER";
   @Column
   private String Phone;
   @Column
   private String Email;

   public String getRole() {
      return this.Role;
   }

   public void setRole(String role) {
      this.Role = role;
   }

   public String getPhone() {
      return this.Phone;
   }

   public void setPhone(String phone) {
      this.Phone = phone;
   }

   public String getEmail() {
      return this.Email;
   }

   public void setEmail(String email) {
      this.Email = email;
   }
}
