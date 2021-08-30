package com.thuannek.repositorys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thuannek.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    
    @Query(value = "SELECT * FROM users WHERE user_email = ?1", nativeQuery = true)
    UserModel findbyEmail(String email);
}