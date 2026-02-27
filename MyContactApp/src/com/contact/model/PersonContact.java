package com.contact.model;

public class PersonContact extends Contact {

    private String birthday;

    public PersonContact(String name, String phone, String email, String birthday) {
        super(name, phone, email);
        this.birthday = birthday;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Birthday: " + birthday);
    }
}