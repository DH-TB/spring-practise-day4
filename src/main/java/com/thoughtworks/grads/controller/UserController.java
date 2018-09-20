package com.thoughtworks.grads.controller;

import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.UserRepository;
import com.thoughtworks.grads.repository.impl.UserRepositoryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserRepository userRepository = new UserRepositoryImpl();

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userRepository.findUser();
    }
}
