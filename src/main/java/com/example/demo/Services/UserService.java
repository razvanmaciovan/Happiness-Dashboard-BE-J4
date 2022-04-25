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

    /* Returns an Optional that contains the User if there is a User with the given Username in the DB or
    an Empty Optional otherwise. */
    public Optional<User> getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }

    /* Returns a List of Users */
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    /* Adds a User to DB */
    public void addUser(User user) {
        userRepo.save(user);
    }

    /* Updates a User inside the DB */
    public Optional<User> updateUser(Long id, User user) {
        Optional<User> userById = userRepo.findById(id); // we search for a User with the given ID
        userById.ifPresent(foundUser -> {
            foundUser.setUsername(user.getUsername());
            foundUser.setPassword(user.getPassword());
            userRepo.save(foundUser);
        }); // if we find it, we update the info
        return userById; // and return it ( it will be returned as an Optional containing the User or an empty one )
    }

    /* Deletes a User with the given ID from the DB */
    public boolean deleteUser(Long id) {
        Optional<User> foundUser = userRepo.findById(id); // we search for a User with the given ID
        if (foundUser.isPresent()) { // if the User was found
            userRepo.deleteById(id); // we delete it and return true
            return true;
        }
        return false; // else we return false ( it means there's no user with such ID )
    }
}
