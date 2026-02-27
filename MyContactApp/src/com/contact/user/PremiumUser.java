package com.contact.user;

public class PremiumUser extends User {

    public PremiumUser(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public String getUserType() {
        return "Premium User";
    }
}

