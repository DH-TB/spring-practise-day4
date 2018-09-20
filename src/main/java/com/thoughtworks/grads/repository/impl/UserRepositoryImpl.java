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
}
