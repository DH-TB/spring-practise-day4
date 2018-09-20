package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Map<Integer, User> USERS = new HashMap<>();

    static {
        USERS.put(1, new User(1, "huanglizhen"));
        USERS.put(2, new User(2, "douqingqing"));
    }

    public static Collection<User> getUser() {
        return USERS.values();
    }
}
