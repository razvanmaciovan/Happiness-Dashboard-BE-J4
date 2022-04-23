package com.example.demo.Controller;

import com.example.demo.Models.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;


    @GetMapping(value = "/users")
    public List<User> getUsers(){

        return userRepo.findAll();
    }

    @PostMapping(value = "/register")
    public void Register(User user){
        userRepo.save(user);
    }

}
