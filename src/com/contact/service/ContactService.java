package com.contact.service;

import com.contact.model.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactService {

    private List<Contact> contactList;

    // Constructor injection (VERY IMPORTANT)
    public ContactService(List<Contact> contactList) {
        this.contactList = contactList;
    }

    // Add contact
    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    // View all contacts
    public void displayAll() {

        if (contactList.isEmpty()) {
            System.out.println("No Contacts Found!");
            return;
        }

        for (Contact c : contactList) {
            c.display();
            System.out.println("---------------------");
        }
    }

    // Bulk Delete
    public void bulkDeleteByEmailDomain(String domain) {

        for (int i = 0; i < contactList.size(); i++) {

            if (contactList.get(i).getEmail().endsWith(domain)) {
                contactList.remove(i);
                i--;
            }
        }

        System.out.println("Bulk delete completed for domain: " + domain);
    }

    // Bulk Update
    public void bulkUpdatePhonePrefix(String oldPrefix, String newPrefix) {

        for (Contact c : contactList) {

            if (c.getPhone().startsWith(oldPrefix)) {

                String newPhone =
                        newPrefix + c.getPhone().substring(oldPrefix.length());

                c.setPhone(newPhone);
            }
        }

        System.out.println("Bulk phone update completed.");
    }

    // Bulk Export
    public List<Contact> bulkExportAfter(java.time.LocalDateTime time) {

        List<Contact> result = new ArrayList<>();

        for (Contact c : contactList) {
            if (c.getCreatedAt().isAfter(time)) {
                result.add(c);
            }
        }

        return result;
    }
}