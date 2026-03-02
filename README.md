UC-11: Create and Manage Tags

Actor: Logged-in User
Description: Users can create tags (e.g., Family, Work) to organize contacts.

Features:

Add, remove, and list tags

Prevent duplicate tags

OOP Concepts:

Tag class with encapsulated fields

Contact-Tag relationship (each contact can have multiple tags)

Java Concepts:

Set<Tag> to ensure unique tags

Override equals() and hashCode() for proper comparison

Usage Example:

Tag family = new Tag("Family");
contact.addTag(family);
