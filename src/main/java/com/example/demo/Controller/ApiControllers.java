package com.example.demo.Controller;

import com.example.demo.Models.User;
import com.example.demo.repo.UserRepo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;


    @GetMapping(value = "/users")
    public List<User> getUsers() {

        return userRepo.findAll();
    }

    /*
     *  function: User getUserById(String id)
     *
     *  description:
     *
     */
    @ApiOperation("Returns user by specified username")
    @ApiParam("Username")
    @GetMapping(value = "/users/{username}")
    public User getUserById(@PathVariable String username) {

        // get all users as a stream
        User user = getUsers().stream()
                // filter by id
                .filter(usr -> username.contentEquals(usr.getUsername()))
                // return first value or return null
                .findFirst().orElse(null);
        /*
         *  NOTE:   this operation should ALWAYS find only one user
         *          since the id's are unique in the database
         *          ( only if the user exists )
         */
        return user;
    }

    @PostMapping(value = "/register")
    public void Register(User user) {
        userRepo.save(user);
    }

}
