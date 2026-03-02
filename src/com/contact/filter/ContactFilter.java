package com.contact.filter;

import com.contact.model.Contact;

public interface ContactFilter {
    boolean apply(Contact contact);
}