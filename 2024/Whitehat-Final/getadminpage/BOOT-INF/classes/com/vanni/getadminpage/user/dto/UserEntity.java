package com.vanni.getadminpage.user.dto;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {
   @Id
   @GeneratedValue(
      strategy = GenerationType.IDENTITY
   )
   private long id;
   @Column(
      nullable = false
   )
   private String username;
   @Column(
      nullable = false
   )
   private String password;
   @Embedded
   private UserInfoEntity userinfo;

   public Long getId() {
      return this.id;
   }

   public String getUsername() {
      return this.username;
   }

   public void setUsername(String username) {
      this.username = username.trim();
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String password) {
      this.password = password.trim();
   }

   public UserInfoEntity getUserinfo() {
      return this.userinfo;
   }

   public void setUserinfo(UserInfoEntity userinfo) {
      this.userinfo = userinfo;
   }
}
