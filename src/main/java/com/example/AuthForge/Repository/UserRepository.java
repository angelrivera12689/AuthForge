package com.example.AuthForge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.AuthForge.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
     List<User> findByActiveTrue();  
     boolean existsByEmail(String email);
}
