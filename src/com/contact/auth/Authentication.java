package com.contact.auth;

import com.contact.user.User;
import java.util.List;

public interface Authentication {

    User login(String email, String credential, List<User> users);
}