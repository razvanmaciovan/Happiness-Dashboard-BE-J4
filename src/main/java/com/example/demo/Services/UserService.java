package com.example.demo.Services;

import com.example.demo.Models.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public void addUser(User user) {
        userRepo.save(user);
    }
}
