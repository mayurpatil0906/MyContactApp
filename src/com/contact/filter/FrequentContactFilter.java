package com.contact.filter;

import com.contact.model.Contact;

public class FrequentContactFilter implements ContactFilter {

    private int minCount;

    public FrequentContactFilter(int minCount) {
        this.minCount = minCount;
    }

    @Override
    public boolean apply(Contact contact) {
        return contact.getContactCount() >= minCount;
    }
}