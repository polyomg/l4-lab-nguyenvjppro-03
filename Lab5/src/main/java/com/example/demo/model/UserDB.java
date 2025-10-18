package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

public class UserDB {
    public static Map<String, User> users = new HashMap<>();

    static {
        // Tài khoản mặc định để test đăng nhập
        users.put("poly", new User("poly", "123", null));
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static User findByUsername(String username) {
        return users.get(username);
    }
}
