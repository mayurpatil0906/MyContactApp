package com.contact.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Contact {

    protected String id;
    protected String name;
    protected String phone;
    protected String email;
    protected LocalDateTime createdAt;

    public Contact(String name, String phone, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Created At: " + createdAt);
    }
}