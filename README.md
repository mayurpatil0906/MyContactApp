UC-01: User Registration

Actor: New User
Description: Users can create an account by providing an email, password, and basic profile information.

Features:

Input validation for email and password

Password storage (plain or hashed)

Unique user identification

OOP Concepts:

Encapsulation: User class with private fields

Methods for setting and validating input

Constructor for initializing a user object

Java Concepts:

Scanner for input

String validation and regular expressions

Exception handling for invalid input

Usage Example:

User newUser = new User("John Doe", "john@example.com", "password123");
System.out.println("User created: " + newUser.getName());
