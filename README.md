UC-02: User Authentication

Actor: Registered User
Description: Users log in with their email and password to access their contacts.

Features:

Login with email and password

Session management (simple logged-in flag)

Authentication check

OOP Concepts:

Interface Authentication with a login() method

Implementation class for login

Polymorphism for different login methods (if extended later)

Java Concepts:

Using Optional to handle login results

String comparison (equals)

Conditional statements for login success/failure

Usage Example:

Authentication auth = new BasicAuth();
boolean success = auth.login("john@example.com", "password123");
System.out.println(success ? "Login successful" : "Login failed");
