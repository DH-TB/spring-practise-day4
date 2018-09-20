package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.User;

import java.util.Collection;

public interface UserRepository {
    Collection<User> findUser();

    void save(User user);

    User updateUser(Integer id, User user);

    void delete(Integer id);

    User getUserById(Integer id);

}
