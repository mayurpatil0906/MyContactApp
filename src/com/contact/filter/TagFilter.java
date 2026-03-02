package com.contact.filter;

import com.contact.model.Contact;

public class TagFilter implements ContactFilter {

    private String tag;

    public TagFilter(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean apply(Contact contact) {
        return contact.getTags().contains(tag);
    }
}