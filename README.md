UC-09: Search Contacts

Actor: Logged-in User
Description: Search contacts by name, phone, email, or tag.

Features:

Multiple search criteria

Returns all matching contacts

OOP Concepts:

Interface Searchable with a search() method

Separate search methods per criteria

Java Concepts:

String comparison (equals, contains)

Loops and conditional statements

Returning a filtered List<Contact>

Usage Example:

List<Contact> results = contactList.stream()
    .filter(c -> c.getName().contains("Alice"))
    .toList();
