package com.contact.model;

public class OrganizationContact extends Contact {

    private String companyName;

    public OrganizationContact(String name, String phone, String email, String companyName) {
        super(name, phone, email);
        this.companyName = companyName;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Company: " + companyName);
    }
}