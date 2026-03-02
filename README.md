UC-10: Basic Filtering

Actor: Logged-in User
Description: Filter contacts by tag, date added, or frequently contacted.

Features:

Apply multiple filters

Sort filtered results

OOP Concepts:

Filter interface with method apply()

Separate methods for different filters

Java Concepts:

Comparator and Collections.sort()

Loops and conditions for filtering

Returning filtered collections

Usage Example:

contacts.sort(Comparator.comparing(Contact::getName));
