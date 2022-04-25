package com.example.demo.Controller;

import com.example.demo.Models.User;
import com.example.demo.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

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
            summary = "Gets all users!",
            description = "This function returns all users from database.\n\n" +
                    "__Usage:__ localhost:8080/api/users"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Users were found and returned successfully!")
    })
    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            summary = "Gets an user",
            description = "This function returns an user by an username given as input.\n\n" +
                    "__Usage:__ localhost:8080/api/users/username"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User was found end returned successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with given username was not found!")
    })
    @GetMapping(path = "/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> foundUser = userService.getUserByUsername(username);
        return foundUser
                .map(user -> new ResponseEntity<>(user, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @Operation(
            summary = "Adds an user",
            description = "This function adds an user to the database.\n\n" +
                    "__Usage:__ localhost:8080/api/register"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User was added successfully to the database!")
    })
    @PostMapping(path = "/register")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @Operation(
            summary = "Updates an User!",
            description = "This function updates an user with the given credentials.\n\n" +
                    "__Usage:__ localhost:8080/api/user/update/id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User was updated successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with given ID was not found in the DataBase!")
    })
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> updatedUser = userService.updateUser(id, user);
        return updatedUser
                .map(value -> new ResponseEntity<>(value, OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @Operation(
            summary = "Deletes an User!",
            description = "This function deletes an user with the given id.\n\n" +
                    "__Usage:__ localhost:8080/api/user/delete/id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User was found and deleted successfully!"),
            @ApiResponse(
                    responseCode = "404",
                    description = "User with given ID was not found in the DataBase!")
    })
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean userWasDeleted = userService.deleteUser(id);
        if (userWasDeleted)
            return new ResponseEntity<>("User with id " + id + " was successfully deleted!", OK);
        return new ResponseEntity<>("No user was found with the given id!", NOT_FOUND);
    }

}
