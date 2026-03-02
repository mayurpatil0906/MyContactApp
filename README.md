UC-04: Create Contact

Actor: Logged-in User
Description: Add a new contact with multiple phone numbers, emails, and optional details.

Features:

Store multiple phones and emails per contact

Unique contact ID

Timestamp for creation

OOP Concepts:

Composition: Contact has PhoneNumber and Email objects

Encapsulation: private fields for contact details

Constructor to initialize contacts

Java Concepts:

List for multiple phone numbers/emails

UUID for unique ID

LocalDateTime for timestamps

Usage Example:

Contact contact = new Contact("Alice", List.of("12345"), List.of("alice@example.com"));
System.out.println(contact);
