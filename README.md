UC-08: Bulk Operations

Actor: Logged-in User
Description: Perform operations on multiple contacts (delete, tag, export).

Features:

Apply same operation to many contacts

Iterate over contact list

Basic file export (optional)

OOP Concepts:

Working with List<Contact> collections

Iteration with loops

Reusable methods for bulk operations

Java Concepts:

For-loop / enhanced for-loop

List and Set handling

File handling for export

Usage Example:

List<Contact> contacts = List.of(contact1, contact2);
contacts.forEach(Contact::delete);
