package com.thoughtworks.grads.controller;

import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.UserRepository;
import com.thoughtworks.grads.repository.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository = new UserRepositoryImpl();

    @GetMapping("/users")
    public Collection<User> queryUsers() {
        return userRepository.findUser();
    }

    @GetMapping("/users/{id}")
    public User queryById(@PathVariable Integer id) {
        return userRepository.findUserById(id);
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userRepository.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity modifyUser(@RequestBody User user) {
        userRepository.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
