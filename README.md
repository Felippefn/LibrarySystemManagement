# 📚 Library System Management

A simple Java Swing-based desktop application to manage a library system with features to add users, add books, and manage book loans.

---

## ✨Features

- **User Management:** Add users with first name, last name, and email.
- **Book Management:** Add books with title, author, genre, and availability status.
- **Loan Management:** Loan books to users for a specified number of days.
- **Display Panels:** View lists of all books, users, and current loans.
- **Simple and Intuitive GUI:** Built with Java Swing components for easy interaction.

---

## Getting Started

### 🌐Prerequisites

- Java Development Kit (JDK) 8 or higher
- An IDE or build tool such as IntelliJ IDEA, Eclipse, or command line with `javac`

### Running the Application

1. Clone the repository or download the source code.
2. Make sure all dependencies (your services and models) are included in the classpath.
3. Compile the project:
   ```bash
   javac -d out src/com/system/library/*.java src/com/system/library/model/*.java src/com/system/library/service/*.java
   ```
Run the main UI class:

## 🤖 Project Structure
```bash
com.system.library
├── LibraryUI.java         # Main UI class with Swing components
├── model
│   ├── Book.java          # Book model class
│   ├── User.java          # User model class
│   └── Loan.java          # Loan model class
├── service
    ├── BookService.java   # Business logic for book operations
    ├── UserService.java   # Business logic for user operations
    └── LoanService.java   # Business logic for loan operations
```
## Usage
- Use the User Panel to add new users.

- Use the Book Panel to add new books.

- Use the Loan Panel to loan books to users (only available books can be loaned).

- The bottom section displays updated lists of all books, users, and loans.

- Messages will notify you on successful operations or errors.

## 📷 Screenshots
![Library UI Screenshot](https://cdn.discordapp.com/attachments/960582309126496349/1381835019252666458/image.png?ex=6848f5a5&is=6847a425&hm=90553a2b3c0de36bbe6995b5d0392e2737155963ef61fbab1ea9759fc8292305&)


### Future Improvements
-- Add return functionality to mark books as returned.

-- Improve input validation and error handling.

-- Add persistent storage (database integration).

-- Add search and filter functionality.

-- Improve UI/UX with better layout and styling.

### License
This project is open source and available under the MIT License.

### 📲 Contact
Created by Felippe — feel free to reach out if you have questions or suggestions!


---
