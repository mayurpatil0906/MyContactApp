package com.contact.auth;

import com.contact.user.User;
import java.util.List;

public class BasicAuth implements Authentication {

    @Override
    public User login(String email, String password, List<User> users) {

        for (User user : users) {
            if (user.getEmail().equals(email) && user.checkPassword(password)) {
                return user;
            }
        }

        return null;
    }
}