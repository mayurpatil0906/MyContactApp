UC-07: Delete Contact

Actor: Logged-in User
Description: Remove a contact with confirmation. Can perform soft or hard delete.

Features:

Soft delete: mark as inactive

Hard delete: remove completely from storage

Confirmation before deletion

OOP Concepts:

Lifecycle management in Contact class

Methods for delete and status update

Java Concepts:

Conditional statements for confirmation

Exception handling for invalid deletion

Boolean flag for soft delete

Usage Example:

contact.delete(); // Marks as deleted
System.out.println("Contact deleted: " + contact.getName());
