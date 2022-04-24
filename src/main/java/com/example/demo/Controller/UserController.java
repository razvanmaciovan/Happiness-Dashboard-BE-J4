package com.example.demo.Controller;

import com.example.demo.Models.User;
import com.example.demo.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            description = "This function returns all users from database.\n\n" +
                    "__Usage:__ localhost:8080/api/users"
    )
    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            description = "This function returns a user by a username given as input.\n\n" +
                    "__Usage:__ localhost:8080/api/users/username"
    )
    @GetMapping(value = "/users/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @Operation(
            description = "This function adds an user to the database.\n\n" +
                    "__Usage:__ localhost:8080/api/register"
    )
    @PostMapping(value = "/register")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

}
