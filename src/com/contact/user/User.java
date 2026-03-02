package com.contact.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

import com.contact.model.Contact;
import com.contact.filter.ContactFilter;

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

    // ================= PASSWORD HASHING =================

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

    // ================= BASIC GETTERS & SETTERS =================

    public String getEmail() { return email; }
    public String getName() { return name; }

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

    // ================= VIEW CONTACTS =================

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

    public void viewContactById(String id) {

        for (Contact c : contacts) {
            if (c.getId().equals(id)) {
                c.display();
                return;
            }
        }

        System.out.println("Contact not found!");
    }

    // ================= EDIT & DELETE =================

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

    // ================= BULK OPERATIONS =================

    public void bulkDeleteByEmailDomain(String domain) {

        for (int i = 0; i < contacts.size(); i++) {

            if (contacts.get(i).getEmail().endsWith(domain)) {
                contacts.remove(i);
                i--;
            }
        }

        System.out.println("Bulk delete completed for domain: " + domain);
    }

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

    public List<Contact> bulkExportAfter(java.time.LocalDateTime time) {

        List<Contact> result = new ArrayList<>();

        for (Contact c : contacts) {
            if (c.getCreatedAt().isAfter(time)) {
                result.add(c);
            }
        }

        return result;
    }

    // ================= SEARCH (UC-09) =================

    public List<Contact> searchByName(String name) {

        List<Contact> result = new ArrayList<>();

        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(c);
            }
        }

        return result;
    }

    public List<Contact> searchByPhone(String phone) {

        List<Contact> result = new ArrayList<>();

        for (Contact c : contacts) {
            if (c.getPhone().contains(phone)) {
                result.add(c);
            }
        }

        return result;
    }

    public List<Contact> searchByEmail(String email) {

        List<Contact> result = new ArrayList<>();

        for (Contact c : contacts) {
            if (c.getEmail().toLowerCase().contains(email.toLowerCase())) {
                result.add(c);
            }
        }

        return result;
    }

    // ================= UC-10 FILTERING =================

    public List<Contact> applyFilter(ContactFilter filter) {

        List<Contact> result = new ArrayList<>();

        for (Contact c : contacts) {
            if (filter.apply(c)) {
                result.add(c);
            }
        }

        return result;
    }

    // ================= SORT BY FREQUENCY =================

    public void sortByFrequency() {

        Collections.sort(contacts, new Comparator<Contact>() {

            @Override
            public int compare(Contact c1, Contact c2) {
                return Integer.compare(
                        c2.getContactCount(),
                        c1.getContactCount()
                );
            }
        });

        System.out.println("Sorted by frequently contacted.");
    }
}