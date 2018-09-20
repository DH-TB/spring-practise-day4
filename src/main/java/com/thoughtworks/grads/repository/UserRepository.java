package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> findUser();
}
