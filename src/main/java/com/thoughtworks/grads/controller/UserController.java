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

    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody User user) {

        return new ResponseEntity<>(userRepository.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}