package com.vanni.getadminpage.user;

import com.vanni.getadminpage.user.dto.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
   UserEntity save(UserEntity user);

   Optional<UserEntity> findByUsername(String name);

   Optional<UserEntity> findById(Long id);

   void delete(UserEntity entity);

   void deleteById(Long id);
}
