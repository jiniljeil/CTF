package com.vanni.getadminpage.user;

import com.vanni.getadminpage.user.dto.UserEntity;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
   private final UserRepository userRepository;

   @Autowired
   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public boolean insertUser(UserEntity user) {
      Optional<UserEntity> sql = this.userRepository.findByUsername(user.getUsername());
      if (!sql.isPresent()) {
         if (user.getUsername().length() > 13 && user.getPassword().length() > 13) {
            UserEntity data = this.userRepository.save(user);
            if (data != null) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public UserEntity LoginUser(UserEntity user) {
      Optional<UserEntity> sql = this.userRepository.findByUsername(user.getUsername());
      UserEntity data = null;
      if (sql.isPresent()) {
         data = (UserEntity)sql.get();
         if (user.getPassword().equals(data.getPassword())) {
            data.setPassword("");
            return data;
         }

         data = null;
      }

      return data;
   }

   public UserEntity selectUser(Long id) {
      UserEntity data = null;
      Optional<UserEntity> sql = this.userRepository.findById(id);
      if (sql.isPresent()) {
         data = (UserEntity)sql.get();
         data.setPassword("");
      }

      return data;
   }

   public UserEntity updateUser(Long id, UserEntity user) {
      UserEntity ret = null;

      try {
         Optional<UserEntity> data = this.userRepository.findById(id);
         if (data.isPresent()) {
            UserEntity retdata = (UserEntity)data.get();
            if (user.getUsername() != null && !user.getUsername().equals("")) {
               retdata.setUsername(user.getUsername());
            }

            if (user.getPassword() != null && !user.getPassword().equals("")) {
               retdata.setPassword(user.getPassword());
            }

            ret = this.userRepository.save(retdata);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return ret;
   }

   public boolean deleteUser(Long id) {
      try {
         this.userRepository.deleteById(id);
         return true;
      } catch (Exception var3) {
         return false;
      }
   }
}
