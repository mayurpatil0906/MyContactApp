package com.contact.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.contact.model.Contact;

public class User {

    private String name;
    private String email;
    private String passwordHash;
    private List<Contact> contacts = new ArrayList<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password");
        }
    }

    public boolean checkPassword(String password) {
        return passwordHash.equals(hashPassword(password));
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String newPassword) {
        this.passwordHash = hashPassword(newPassword);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getUserType() {
        return "Normal User";
    }
    public void viewContacts() {

        if (contacts.isEmpty()) {
            System.out.println("No Contacts Found!");
            return;
        }

        System.out.println("\n---- Your Contacts ----");

        for (Contact c : contacts) {
            System.out.println("Name  : " + c.getName());
            System.out.println("Phone : " + c.getPhone());
            System.out.println("Email : " + c.getEmail());
            System.out.println("-------------------------");
        }
    }
    
}