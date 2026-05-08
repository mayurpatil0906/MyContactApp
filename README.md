# 📇 MyContacts App

> A Java-based, console-driven contact management system built as a **pedagogical tool** for teaching Object-Oriented Programming, design patterns, and core Java concepts through a real-world use case.

[![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=java)](https://www.java.com)
[![OOP](https://img.shields.io/badge/Paradigm-Object--Oriented-blue)]()
[![Design Patterns](https://img.shields.io/badge/Patterns-12%2B%20Applied-green)]()
[![Use Cases](https://img.shields.io/badge/Use%20Cases-UC01--UC12-purple)]()
[![License](https://img.shields.io/badge/License-MIT-lightgrey)]()

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features at a Glance](#features-at-a-glance)
- [System Architecture](#system-architecture)
- [Use Case Breakdown](#use-case-breakdown)
  - [UC01 — User Registration](#uc01--user-registration)
  - [UC02 — User Authentication](#uc02--user-authentication)
  - [UC03 — User Profile Management](#uc03--user-profile-management)
  - [UC04 — Create Contact](#uc04--create-contact)
  - [UC05 — View Contact Details](#uc05--view-contact-details)
  - [UC06 — Edit Contact](#uc06--edit-contact)
  - [UC07 — Delete Contact](#uc07--delete-contact)
  - [UC08 — Bulk Operations](#uc08--bulk-operations)
  - [UC09 — Search Contacts](#uc09--search-contacts)
  - [UC10 — Advanced Filtering & Sorting](#uc10--advanced-filtering--sorting)
  - [UC11 — Create & Manage Tags](#uc11--create--manage-tags)
  - [UC12 — Apply Tags to Contacts](#uc12--apply-tags-to-contacts)
- [OOP Concepts Demonstrated](#oop-concepts-demonstrated)
- [Design Patterns Reference](#design-patterns-reference)
- [Core Java Building Blocks](#core-java-building-blocks)
- [Class Hierarchy](#class-hierarchy)
- [Key Relationships](#key-relationships)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Educational Goals](#educational-goals)

---

## Overview

**MyContacts** is a console-driven Java application that models a contact management system — from user registration and authentication through contact creation, editing, tagging, searching, and bulk operations. Every feature is implemented **use-case by use-case**, making it ideal for learning how real-world OOP design decisions are made incrementally.

```
User Registration / Login
        │
        ▼
   Contact Management ──▶ Create / View / Edit / Delete
        │
        ▼
   Organisation ──────────▶ Tags / Groups / Bulk Ops
        │
        ▼
   Discovery ─────────────▶ Search / Filter / Sort
```

Each use case introduces a specific OOP concept, design pattern, or Java API — so the complexity builds naturally as the application grows.

---

## Features at a Glance

| Area | Features |
|---|---|
| 👤 User Management | Registration, Login, Profile, Password Management |
| 📇 Contacts | Create Person & Organisation contacts, view with decorator formatting |
| ✏️ Editing | Full edit with Undo/Redo via Command + Memento pattern |
| 🗑️ Deletion | Soft delete (archive) and hard delete with confirmation |
| 🏷️ Tagging | Custom tags (Family, Work, Friends) with many-to-many mapping |
| 👥 Groups | Composite groups with bulk operations |
| 🔍 Search | Name, phone, email, tag search via Specification pattern |
| ⚙️ Filter & Sort | Multi-criteria filtering and sorting via Strategy pattern |
| 🔐 Security | Hashed passwords, session management, role-based access |
| 🖥️ Admin | User oversight and global search capabilities |

---

## System Architecture

```
┌──────────────────────────────────────────────────────────────┐
│                      Presentation Layer                       │
│            ConsoleUI  │  MenuSystem  │  DisplayFormatters     │
├──────────────────────────────────────────────────────────────┤
│                       Service Layer                           │
│  UserService  │  ContactService  │  TagService               │
│  SearchService  │  FilterService  │  GroupService            │
├──────────────────────────────────────────────────────────────┤
│                       Domain Layer                            │
│  User (FreeUser, PremiumUser)                                 │
│  Contact (Person, Organisation)                               │
│  PhoneNumber  │  Email  │  Tag  │  Group                     │
├──────────────────────────────────────────────────────────────┤
│                     Repository Layer                          │
│        ContactRepository  │  UserRepository                  │
│        (In-memory with List/Map collections)                  │
├──────────────────────────────────────────────────────────────┤
│                    Cross-Cutting Concerns                     │
│       Validation  │  Exception Handling  │  Session Mgmt      │
└──────────────────────────────────────────────────────────────┘
```

---

## Use Case Breakdown

---

### UC01 — User Registration

**Actor:** New User
**Goal:** Create an account to access the contact management system.

**OOP Concepts:**
- `User` class with encapsulated private fields
- Validation logic hidden behind public methods
- Password hashing inside the `User` class (not exposed externally)

**Design Patterns:**

| Pattern | Application |
|---|---|
| Factory Pattern | Creates `FreeUser` or `PremiumUser` based on registration type |
| Builder Pattern | Constructs `User` objects step-by-step with optional fields |

**Java Concepts:**
- Regular expressions for email format validation
- `MessageDigest` for password hashing (SHA-256)
- Custom `UserAlreadyExistsException`

---

### UC02 — User Authentication

**Actor:** Registered User
**Goal:** Log in with credentials and establish a session.

**OOP Concepts:**
- `Authentication` interface with multiple concrete implementations
- Polymorphism — calling `authenticate()` works regardless of strategy

**Design Patterns:**

| Pattern | Application |
|---|---|
| Strategy Pattern | Pluggable auth: `BasicAuthStrategy`, `OAuthStrategy` |
| Singleton Pattern | `SessionManager` — single session state across the app |

**Java Concepts:**
- `Optional<User>` for safe null handling on login result
- `MessageDigest` for credential verification
- Session token generation with `UUID`

---

### UC03 — User Profile Management

**Actor:** Logged-in User
**Goal:** Update profile info, change password, or manage preferences.

**OOP Concepts:**
- Setter methods with built-in validation (e.g., password strength check)
- JavaBeans conventions for property access

**Design Patterns:**

| Pattern | Application |
|---|---|
| Command Pattern | Each profile update wrapped as a `Command` object (enables undo) |

**Java Concepts:**
- JavaBeans conventions (`getXxx()`, `setXxx()`, `isXxx()`)
- Security best practices for password change (require current password)
- Validation before any state change is committed

---

### UC04 — Create Contact

**Actor:** Logged-in User
**Goal:** Add a new contact with full details.

**OOP Concepts:**
- `Contact` class hierarchy: `Contact` → `Person`, `Organisation`
- **Composition**: `Contact` *has* `PhoneNumber` and `Email` objects
- Multiple phone numbers / emails per contact via `List<>`

**Design Patterns:**

| Pattern | Application |
|---|---|
| Builder Pattern | `ContactBuilder` for step-by-step construction with optional fields |
| Factory Pattern | `ContactFactory.create(ContactType.PERSON)` vs `ORGANISATION` |

**Java Concepts:**
- `List<PhoneNumber>`, `List<Email>` for multi-value fields
- `LocalDateTime` for `createdAt` / `updatedAt` timestamps
- `UUID.randomUUID()` for unique contact IDs

---

### UC05 — View Contact Details

**Actor:** Logged-in User
**Goal:** Display full contact information in a formatted view.

**OOP Concepts:**
- `toString()` override for display-ready output
- `Optional<T>` for nullable fields (e.g., address, birthday)
- Immutable view objects prevent accidental mutation

**Design Patterns:**

| Pattern | Application |
|---|---|
| Decorator Pattern | `UpperCaseDecorator` wraps name display; `MaskedEmailDecorator` masks email |

**Decorator Chain Example:**
```
BaseContactDisplay
    └──▶ UpperCaseDecorator
              └──▶ MaskedEmailDecorator
                        └──▶ final display output
```

**Java Concepts:**
- String formatting with `String.format()`
- `Optional.ifPresent()` for optional field display
- Immutable view/DTO pattern

---

### UC06 — Edit Contact

**Actor:** Logged-in User
**Goal:** Modify existing contact details, with the ability to undo/redo changes.

**OOP Concepts:**
- Copy constructor for creating modified versions without mutating original
- **Defensive copying** — internal state is never directly exposed
- Deep copy vs shallow copy distinction

**Design Patterns:**

| Pattern | Application |
|---|---|
| Command Pattern | `EditNameCommand`, `EditPhoneCommand` — each edit is a reversible object |
| Memento Pattern | Snapshot of contact state before edit; restored on undo |

**Undo/Redo Flow:**
```
Edit triggered
    │
    ▼
Memento saved ──▶ Command executed ──▶ Push to undoStack
                                              │
                                    Undo ─────┘──▶ Pop from undoStack
                                                    Push to redoStack
```

**Java Concepts:**
- `Deque<Command>` for undo/redo stacks
- Deep copy of `List<PhoneNumber>` to avoid aliasing bugs
- Validation before state change is committed

---

### UC07 — Delete Contact

**Actor:** Logged-in User
**Goal:** Remove a contact with confirmation; support both soft and hard delete.

**OOP Concepts:**
- Lifecycle management — contact states: `ACTIVE`, `DELETED`, `ARCHIVED`
- Cascade considerations for related tags and group memberships

**Design Patterns:**

| Pattern | Application |
|---|---|
| Observer Pattern | Notifies `GroupService` and `TagService` when a contact is deleted |

**Delete Modes:**

| Mode | Behaviour |
|---|---|
| **Soft Delete** | Sets `isDeleted = true`; contact remains in storage but is hidden from views |
| **Hard Delete** | Permanently removes from all collections; non-reversible |

**Java Concepts:**
- `boolean isDeleted` flag for soft delete
- `Iterator.remove()` for safe removal during iteration
- Console confirmation prompt before hard delete

---

### UC08 — Bulk Operations

**Actor:** Logged-in User
**Goal:** Perform actions (delete, tag, export) on multiple contacts at once.

**OOP Concepts:**
- Collection operations with filtering predicates
- Treating individual contacts and groups uniformly

**Design Patterns:**

| Pattern | Application |
|---|---|
| Composite Pattern | `ContactGroup` and `Contact` both implement `ContactComponent`; bulk ops work on either |

**Supported Bulk Operations:**
- Bulk tag assignment
- Bulk soft / hard delete
- Bulk export to CSV
- Bulk group membership update

**Java Concepts:**
- `Stream API` — `filter()`, `map()`, `collect()`
- Lambda expressions for inline predicates
- Method references (`ContactService::softDelete`)
- Batch processing with transaction-like rollback on error

---

### UC09 — Search Contacts

**Actor:** Logged-in User
**Goal:** Find contacts by name, phone, email, tag, or any combination.

**OOP Concepts:**
- `SearchCriteria` interface with multiple implementations
- Composition for combining multiple criteria into complex queries

**Design Patterns:**

| Pattern | Application |
|---|---|
| Specification Pattern | `NameSpec`, `PhoneSpec`, `EmailSpec`, `TagSpec` — composable with `and()`, `or()`, `not()` |
| Chain of Responsibility | Filter pipeline: each handler processes or passes down the chain |

**Search Capability Matrix:**

| Search Type | Implementation Class | Match Type |
|---|---|---|
| By Name | `NameSpecification` | Case-insensitive partial match |
| By Phone | `PhoneSpecification` | Exact or prefix match |
| By Email | `EmailSpecification` | Regex pattern match |
| By Tag | `TagSpecification` | Exact tag name match |
| Combined | `AndSpecification` / `OrSpecification` | Composite logic |

**Java Concepts:**
- `Predicate<Contact>` functional interface
- `Stream.filter()` with composed predicates
- Case-insensitive search: `String.toLowerCase()`
- Regex with `Pattern` and `Matcher`

---

### UC10 — Advanced Filtering & Sorting

**Actor:** Logged-in User
**Goal:** Narrow down contacts using multiple filters and sort by various criteria.

**OOP Concepts:**
- `Filter` interface hierarchy with composite filters
- Interchangeable sort algorithms via interface

**Design Patterns:**

| Pattern | Application |
|---|---|
| Strategy Pattern | `SortByName`, `SortByDateAdded`, `SortByFrequency` — swappable at runtime |
| Composite Pattern | Combine multiple `Filter` objects: `AndFilter`, `OrFilter` |

**Sort Options:**

| Strategy Class | Sorts By |
|---|---|
| `NameSortStrategy` | Alphabetical (A–Z / Z–A) |
| `DateAddedSortStrategy` | Newest / Oldest first |
| `FrequencySortStrategy` | Most frequently accessed contacts first |
| `TagSortStrategy` | Grouped by tag name |

**Java Concepts:**
- `Comparator<Contact>` with `thenComparing()` for multi-level sort
- `Comparator.reversed()` for descending order
- Functional interfaces and lambda-based comparators

---

### UC11 — Create & Manage Tags

**Actor:** Logged-in User
**Goal:** Create custom labels to organise contacts into categories.

**OOP Concepts:**
- `Tag` class with validation (no duplicates, non-empty name)
- Many-to-many relationship: a Contact can have many Tags; a Tag can belong to many Contacts

**Design Patterns:**

| Pattern | Application |
|---|---|
| Flyweight Pattern | Tag instances are shared — `TagRegistry` ensures only one object per tag name |

**Predefined Tag Categories (EnumSet):**
```java
enum PredefinedTag { FAMILY, WORK, FRIENDS, EMERGENCY, VIP }
```

**Java Concepts:**
- `Set<Tag>` — enforces uniqueness via `equals()` + `hashCode()` override
- `EnumSet` for predefined tag categories
- `equals()` based on tag name (case-insensitive)

---

### UC12 — Apply Tags to Contacts

**Actor:** Logged-in User
**Goal:** Assign or remove one or more tags from a contact.

**OOP Concepts:**
- Association class managing the Contact ↔ Tag relationship
- Bidirectional relationship management (both sides updated atomically)

**Design Patterns:**

| Pattern | Application |
|---|---|
| Observer Pattern | When a tag is removed, all group memberships based on that tag are notified and updated |

**Java Concepts:**
- `Set.add()` / `Set.remove()` for tag assignment
- Bidirectional update: `contact.addTag(tag)` also calls `tag.addContact(contact)`
- `Set` operations — union, intersection for tag-based queries

---

## OOP Concepts Demonstrated

| Pillar | Where Applied |
|---|---|
| **Encapsulation** | Private fields + validated setters in `User`, `Contact`, `Tag`; password hashing hidden inside `User` |
| **Inheritance** | `Contact → Person / Organisation`; `User → FreeUser / PremiumUser`; filter/search interface trees |
| **Polymorphism** | `Authentication.authenticate()` dispatches to correct strategy; `Filter.apply()` works on any filter type |
| **Abstraction** | `Authentication`, `SearchCriteria`, `Filter`, `ContactComponent` interfaces hide implementation detail |

---

## Design Patterns Reference

### Creational

| Pattern | Applied In |
|---|---|
| Factory | `ContactFactory`, `UserFactory` |
| Builder | `ContactBuilder`, `UserBuilder` |
| Singleton | `SessionManager`, `TagRegistry` |

### Structural

| Pattern | Applied In |
|---|---|
| Decorator | Contact display formatters (`UpperCase`, `MaskedEmail`) |
| Composite | `ContactGroup` + `Contact` implement `ContactComponent` |
| Flyweight | Shared `Tag` instances via `TagRegistry` |

### Behavioral

| Pattern | Applied In |
|---|---|
| Strategy | Auth strategies, sort strategies, filter algorithms |
| Observer | Tag removal → group update; contact deletion → service notifications |
| Command | Edit operations (undo/redo stack) |
| Memento | Contact state snapshot before edit |
| Chain of Responsibility | Search filter pipeline |
| Specification | Composable search criteria (`and`, `or`, `not`) |

---

## Core Java Building Blocks

### Collections Framework

```java
List<PhoneNumber>               // Multiple phones per contact
List<Email>                     // Multiple emails per contact
Set<Tag>                        // Unique tags per contact (no duplicates)
Set<Contact>                    // Contact collections in groups
Map<String, Contact>            // ID → Contact for O(1) lookup
EnumSet<PredefinedTag>          // Predefined tag categories
Deque<Command>                  // Undo / redo stack
```

### Functional Programming (Java 8+)

```java
Predicate<Contact>              // Search and filter conditions
Comparator<Contact>             // Multi-level sorting logic
Stream<Contact>                 // Filtering, mapping, collecting
.filter(c -> c.hasTag("Work"))  // Lambda inline predicate
ContactService::softDelete      // Method reference
```

### Java Standard APIs

```java
UUID.randomUUID()               // Unique contact and user IDs
LocalDateTime.now()             // Creation and modification timestamps
Optional<Contact>               // Safe handling of nullable results
Optional<User>                  // Login result without null checks
MessageDigest                   // SHA-256 password hashing
Pattern / Matcher               // Regex for search and email validation
```

---

## Class Hierarchy

```
Object
├── User
│   ├── FreeUser
│   └── PremiumUser
│
├── Contact  (implements ContactComponent)
│   ├── Person
│   └── Organisation
│
├── ContactGroup  (implements ContactComponent)
│
├── Tag
├── PhoneNumber
├── Email
│
├── ContactDisplayDecorator  (abstract)
│   ├── UpperCaseDecorator
│   └── MaskedEmailDecorator
│
├── Command  (interface)
│   ├── EditNameCommand
│   ├── EditPhoneCommand
│   └── UpdateProfileCommand
│
├── SearchCriteria / Specification  (interface)
│   ├── NameSpecification
│   ├── PhoneSpecification
│   ├── EmailSpecification
│   ├── TagSpecification
│   ├── AndSpecification
│   └── OrSpecification
│
├── SortStrategy  (interface)
│   ├── NameSortStrategy
│   ├── DateAddedSortStrategy
│   └── FrequencySortStrategy
│
└── Authentication  (interface)
    ├── BasicAuthStrategy
    └── OAuthStrategy
```

---

## Key Relationships

```
User ──────────────────── manages ──────────────────▶ Contact (0..*)
                                                          │
Contact ◆─────────── composed of ──────────────────▶ PhoneNumber (1..*)
Contact ◆─────────── composed of ──────────────────▶ Email (1..*)
Contact ◆─────────── associated with ──────────────▶ Tag (0..*) [many-to-many]
Contact ◆─────────── member of ────────────────────▶ ContactGroup (0..*)

ContactGroup ─────── aggregates ───────────────────▶ Contact (0..*)

User ─────────────── has one ──────────────────────▶ SessionManager [Singleton]
User ─────────────── uses ─────────────────────────▶ Authentication [Strategy]
```

| Relationship | Type | Description |
|---|---|---|
| `Contact` has `PhoneNumber` | **Composition** | Phone numbers cannot exist without a Contact |
| `Contact` has `Email` | **Composition** | Emails cannot exist without a Contact |
| `Contact` ↔ `Tag` | **Association** | Many-to-many; managed bidirectionally |
| `User` → `Contact` | **Aggregation** | User owns contacts; contacts have independent lifecycle |
| `ContactGroup` → `Contact` | **Aggregation** | Groups hold references; contacts exist independently |

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8+ (optional, for build management)

### Clone & Run

```bash
# Clone the repository
git clone https://github.com/your-username/mycontacts-app.git
cd mycontacts-app

# Compile
javac -d out src/**/*.java

# Run
java -cp out com.mycontacts.Main
```

### With Maven

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.mycontacts.Main"
```

---

## Project Structure

```
mycontacts-app/
│
├── src/
│   └── com/mycontacts/
│       ├── Main.java                        # Entry point
│       │
│       ├── domain/                          # Core entities
│       │   ├── User.java
│       │   ├── FreeUser.java
│       │   ├── PremiumUser.java
│       │   ├── Contact.java
│       │   ├── Person.java
│       │   ├── Organisation.java
│       │   ├── PhoneNumber.java
│       │   ├── Email.java
│       │   ├── Tag.java
│       │   └── ContactGroup.java
│       │
│       ├── service/                         # Business logic
│       │   ├── UserService.java
│       │   ├── ContactService.java
│       │   ├── TagService.java
│       │   ├── SearchService.java
│       │   └── GroupService.java
│       │
│       ├── patterns/
│       │   ├── factory/                     # Factory & Builder
│       │   ├── strategy/                    # Auth & Sort strategies
│       │   ├── decorator/                   # Display decorators
│       │   ├── command/                     # Edit commands + undo/redo
│       │   ├── memento/                     # State snapshots
│       │   ├── observer/                    # Delete/tag events
│       │   ├── composite/                   # ContactComponent tree
│       │   ├── specification/               # Search criteria
│       │   └── flyweight/                   # Tag registry
│       │
│       ├── validation/                      # Input validation
│       │   ├── InputValidator.java
│       │   └── exceptions/
│       │       ├── ValidationException.java
│       │       ├── UserAlreadyExistsException.java
│       │       └── ContactNotFoundException.java
│       │
│       ├── repository/                      # In-memory data access
│       │   ├── UserRepository.java
│       │   └── ContactRepository.java
│       │
│       └── ui/                              # Console UI
│           ├── MainMenu.java
│           ├── ContactMenu.java
│           └── DisplayFormatter.java
│
├── test/                                    # Unit tests
│   └── com/mycontacts/
│       ├── service/
│       └── patterns/
│
├── pom.xml
└── README.md
```

---

## Educational Goals

This project is structured so that each use case teaches one or more distinct concepts. Here is the learning progression:

| Stage | Use Cases | Concepts Introduced |
|---|---|---|
| **Foundation** | UC01–UC02 | Classes, encapsulation, interfaces, Factory, Builder, Singleton |
| **Core Entities** | UC03–UC05 | Inheritance, composition, `Optional`, Decorator, `toString()` override |
| **State Management** | UC06–UC07 | Command, Memento, deep copy, Observer, soft/hard delete |
| **Collections** | UC08 | Stream API, lambdas, method references, Composite pattern |
| **Query Engine** | UC09–UC10 | Predicate, Specification, Strategy, `Comparator`, multi-level sorting |
| **Relationships** | UC11–UC12 | Many-to-many, Flyweight, bidirectional associations, `equals()`/`hashCode()` |

By the end of the project, students will have applied **12 design patterns**, worked with **6+ collection types**, used **functional programming APIs**, and built a fully functional OOP system from scratch.

---

## Disclaimer

> This application is a **software engineering learning project**. All data is stored in memory and does not persist between runs. It is intended purely as an educational tool for studying Object-Oriented Programming and Java design patterns.

---

<p align="center">Built for learning. Designed with patterns. Written in Java.</p>
