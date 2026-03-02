package com.contact.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Contact {

    private String id;
    private String name;
    private String phone;
    private String email;
    private LocalDateTime createdAt;

    private Set<Tag> tags = new HashSet<>();
    private int contactCount = 0;

    public Contact(String name, String phone, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }

    // ================= GETTERS =================

    public String getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Set<Tag> getTags() { return tags; }
    public int getContactCount() { return contactCount; }

    // ================= SETTERS =================

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public void setPhone(String phone) {
        if (phone != null && phone.matches("\\d{10}")) {
            this.phone = phone;
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    // ================= TAG METHODS =================

    public void addTag(Tag tag) {
        if (tag != null) {
            tags.add(tag);
        }
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    // ================= FREQUENCY =================

    public void incrementContactCount() {
        contactCount++;
    }

    // ================= DISPLAY =================

    public void display() {

        incrementContactCount();

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Tags: " + tags);
        System.out.println("Created At: " + createdAt);
        System.out.println("Contacted: " + contactCount + " times");
    }
}