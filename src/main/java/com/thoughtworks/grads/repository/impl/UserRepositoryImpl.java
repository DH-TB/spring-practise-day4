package com.thoughtworks.grads.repository.impl;

import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.UserRepository;
import com.thoughtworks.grads.repository.UserStorage;

import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Collection<User> findUser() {
        return UserStorage.getUser();
    }

    @Override
    public void saveUser(User user) {
        UserStorage.saveUser(user);
    }

    @Override
    public User findUserById(Integer id) {
        return UserStorage.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        UserStorage.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        UserStorage.deleteUserById(id);
    }
}
