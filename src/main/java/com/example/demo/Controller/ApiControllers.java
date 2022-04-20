package com.example.demo.Controller;

import com.example.demo.Models.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage(){

        return "Haha Codez22";

    }
    @GetMapping(value = "/users")
    public List<User> getUsers(){

        return userRepo.findAll();
    }

    @PostMapping(value = "/register")
    public void Register(User user){
        userRepo.save(user);
    }

}
