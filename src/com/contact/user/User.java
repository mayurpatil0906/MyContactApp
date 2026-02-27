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
//to check password 
    public boolean checkPassword(String password) {
        return passwordHash.equals(hashPassword(password));
    }
//getter method to get email
    public String getEmail() {
        return email;
    }
  //getter method to get name
    public String getName() {
        return name;
    }
  //getter method to set name
    public void setName(String name) {
        this.name = name;
    }
  //getter method to set strong password
    public void setPassword(String newPassword) {
        this.passwordHash = hashPassword(newPassword);
    }
//method to add contact
    public void addContact(Contact contact) {
        contacts.add(contact);
    }
//list to get all contacts
    public List<Contact> getContacts() {
        return contacts;
    }

    public String getUserType() {
        return "Normal User";
    }
    // to view all contacts
    public void viewContacts() {

        if (contacts.isEmpty()) {
            System.out.println("No Contacts Found!");
            return;
        }

        System.out.println("\n---- Your Contacts ----");

        for (Contact c : contacts) {
            System.out.println("ID   : " + c.getId());
            System.out.println("Name : " + c.getName());
            System.out.println("Phone: " + c.getPhone());
            System.out.println("Email: " + c.getEmail());
            System.out.println("-----------------------");
        }
    }
    //after adding we will get the id using that id contact will be shown
    public void viewContactById(String id) {

        for (Contact c : contacts) {
            if (c.getId().equals(id)) {
                System.out.println("\n--- Contact Details ---");
                c.display();
                return;
            }
        }

        System.out.println("Contact not found!");
    }
 // Edit contact by ID
    public void editContact(String id, String newName, String newPhone, String newEmail) {

        for (Contact c : contacts) {

            if (c.getId().equals(id)) {

                c.setName(newName);
                c.setPhone(newPhone);
                c.setEmail(newEmail);

                System.out.println("Contact Updated Successfully!");
                return;
            }
        }

        System.out.println("Contact Not Found!");
    }
 // Deletes contact by ID
    public void deleteContactById(String id) {

        for (int i = 0; i < contacts.size(); i++) {

            if (contacts.get(i).getId().equals(id)) {
                contacts.remove(i);
                System.out.println("Contact Deleted Successfully!");
                return;
            }
        }

        System.out.println("Contact Not Found!");
    }
 // 🔹 BULK DELETE
    public void bulkDeleteByEmailDomain(String domain) {

        for (int i = 0; i < contacts.size(); i++) {

            if (contacts.get(i).getEmail().endsWith(domain)) {
                contacts.remove(i);
                i--;
            }
        }

        System.out.println("Bulk delete completed for domain: " + domain);
    }

    // 🔹 BULK UPDATE
    public void bulkUpdatePhonePrefix(String oldPrefix, String newPrefix) {

        for (Contact c : contacts) {

            if (c.getPhone().startsWith(oldPrefix)) {

                String newPhone =
                        newPrefix + c.getPhone().substring(oldPrefix.length());

                c.setPhone(newPhone);
            }
        }

        System.out.println("Bulk phone update completed.");
    }

    // 🔹 BULK EXPORT
    public List<Contact> bulkExportAfter(java.time.LocalDateTime time) {

        List<Contact> result = new ArrayList<>();

        for (Contact c : contacts) {
            if (c.getCreatedAt().isAfter(time)) {
                result.add(c);
            }
        }

        return result;
    }
    
    
    

}