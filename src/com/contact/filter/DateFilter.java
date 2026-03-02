package com.contact.filter;

import java.time.LocalDateTime;
import com.contact.model.Contact;

public class DateFilter implements ContactFilter {

    private LocalDateTime afterDate;

    public DateFilter(LocalDateTime afterDate) {
        this.afterDate = afterDate;
    }

    @Override
    public boolean apply(Contact contact) {
        return contact.getCreatedAt().isAfter(afterDate);
    }
}