package com.contact.auth;

import com.contact.user.User;
import java.util.List;

public class OAuthAuth implements Authentication {

    @Override
    public User login(String email, String token, List<User> users) {

        // simple token validation
        if (!token.equals("OAUTH123")) {
            return null;
        }

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
    }
}