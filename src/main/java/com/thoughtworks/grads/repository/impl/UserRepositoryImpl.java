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
    public void save(User user) {
        UserStorage.saveUser(user);
    }

    @Override
    public User updateUser(Integer id, User user) {
        return UserStorage.updateUser(id, user);
    }

    @Override
    public void delete(Integer id) {
        UserStorage.delete(id);
    }

    @Override
    public User getUserById(Integer id) {
        return UserStorage.getUserById(id);
    }
}
