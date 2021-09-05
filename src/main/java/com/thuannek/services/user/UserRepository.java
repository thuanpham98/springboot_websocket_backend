package com.thuannek.services.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thuannek.services.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    
    @Query(value = "SELECT * FROM users WHERE user_email = ?1", nativeQuery = true)
    UserEntity findbyEmail(String email);
}