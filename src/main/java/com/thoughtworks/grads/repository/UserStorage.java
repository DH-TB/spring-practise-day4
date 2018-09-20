package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Map<Integer, User> USERS = new HashMap<>();

    public static Collection<User> getUser() {
        return USERS.values();
    }

    public static void clear() {
        USERS.clear();
    }

    public static void add(User... users) {
        Arrays.stream(users).forEach(user -> USERS.put(user.getId(), user));
    }

    public static void saveUser(User user) {
        USERS.put(user.getId(), user);
    }

    public static int getSize() {
        return USERS.size();
    }
}
