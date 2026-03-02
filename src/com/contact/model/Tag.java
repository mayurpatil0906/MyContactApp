package com.contact.model;

import java.util.Objects;

public class Tag {

    private String name;

    public Tag(String name) {
        this.name = name.toLowerCase(); // store in lowercase for consistency
    }

    public String getName() {
        return name;
    }

    // Override equals() to prevent duplicate tags
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Tag tag = (Tag) obj;

        return Objects.equals(name, tag.name);
    }

    // Override hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}