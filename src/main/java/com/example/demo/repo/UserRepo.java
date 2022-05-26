package com.example.demo.repo;

import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@SuppressWarnings("unused")
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
}
