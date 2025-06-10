package com.system.library;

import com.system.library.model.Book;
import com.system.library.model.User;
import com.system.library.model.Loan;
import com.system.library.service.BookService;
import com.system.library.service.UserService;
import com.system.library.service.LoanService;

import javax.swing.*;
import java.awt.*;

public class LibraryUI {
    private JFrame frame;

    // Display areas for data display (class members)
    private JTextArea displayAreaBooks;   // For Books
    private JTextArea displayAreaUsers;   // For Users
    private JTextArea displayAreaLoans;   // For Loans

    private BookService bookService;
    private UserService userService;
    private LoanService loanService;

    public LibraryUI(BookService bookService, UserService userService, LoanService loanService) {
        this.bookService = bookService;
        this.userService = userService;
        this.loanService = loanService;

        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Library System Management");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // ===== Input Panels (Add User, Add Book, Loan Book) =====
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add User Panel
        JPanel userInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userInputPanel.setBorder(BorderFactory.createTitledBorder("Add User"));
        JTextField firstNameField = new JTextField(10);
        JTextField lastNameField = new JTextField(10);
        JTextField emailField = new JTextField(15);
        JButton addUserButton = new JButton("Add User 👤");

        addUserButton.addActionListener(e -> {
            String first = firstNameField.getText().trim();
            String last = lastNameField.getText().trim();
            String email = emailField.getText().trim();

            if (first.isEmpty() || last.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all user fields.");
                return;
            }

            userService.addUser(new User(first, last, email));
            JOptionPane.showMessageDialog(frame, "User added successfully!");
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            refreshUserDisplay();
        });

        userInputPanel.add(new JLabel("First Name:"));
        userInputPanel.add(firstNameField);
        userInputPanel.add(new JLabel("Last Name:"));
        userInputPanel.add(lastNameField);
        userInputPanel.add(new JLabel("Email:"));
        userInputPanel.add(emailField);
        userInputPanel.add(addUserButton);

        // Add Book Panel
        JPanel bookInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bookInputPanel.setBorder(BorderFactory.createTitledBorder("Add Book"));
        JTextField titleField = new JTextField(10);
        JTextField authorField = new JTextField(10);
        JTextField genreField = new JTextField(10);
        JButton addBookButton = new JButton("Add Book 📚");

        addBookButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String genre = genreField.getText().trim();

            if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all book fields.");
                return;
            }

            bookService.addBook(new Book(title, author, genre, true));
            JOptionPane.showMessageDialog(frame, "Book added successfully!");
            titleField.setText("");
            authorField.setText("");
            genreField.setText("");
            refreshBookDisplay();
        });

        bookInputPanel.add(new JLabel("Title:"));
        bookInputPanel.add(titleField);
        bookInputPanel.add(new JLabel("Author:"));
        bookInputPanel.add(authorField);
        bookInputPanel.add(new JLabel("Genre:"));
        bookInputPanel.add(genreField);
        bookInputPanel.add(addBookButton);

        // Loan Book Panel
        JPanel loanInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loanInputPanel.setBorder(BorderFactory.createTitledBorder("Loan Book"));
        JTextField loanUserEmail = new JTextField(15);
        JTextField loanBookTitle = new JTextField(10);
        JTextField loanDays = new JTextField(5);
        JButton loanButton = new JButton("Loan Book 🔄");

        loanButton.addActionListener(e -> {
            String email = loanUserEmail.getText().trim();
            String title = loanBookTitle.getText().trim();
            String daysText = loanDays.getText().trim();

            if (email.isEmpty() || title.isEmpty() || daysText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all loan fields.");
                return;
            }

            int days;
            try {
                days = Integer.parseInt(daysText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number of days.");
                return;
            }

            User user = userService.getUserByEmail(email);
            Book book = bookService.getBookByTitle(title);

            if (user == null) {
                JOptionPane.showMessageDialog(frame, "User not found.");
                return;
            }
            if (book == null) {
                JOptionPane.showMessageDialog(frame, "Book not found.");
                return;
            }
            if (!book.getAvailable()) {
                JOptionPane.showMessageDialog(frame, "Book is currently unavailable.");
                return;
            }

            loanService.createLoan(user, book, days);
            JOptionPane.showMessageDialog(frame, "Loan created successfully!");
            loanUserEmail.setText("");
            loanBookTitle.setText("");
            loanDays.setText("");

            refreshBookDisplay();
            refreshLoanDisplay();
        });

        loanInputPanel.add(new JLabel("User Email:"));
        loanInputPanel.add(loanUserEmail);
        loanInputPanel.add(new JLabel("Book Title:"));
        loanInputPanel.add(loanBookTitle);
        loanInputPanel.add(new JLabel("Days:"));
        loanInputPanel.add(loanDays);
        loanInputPanel.add(loanButton);

        // Add all input panels to the inputPanel (vertical layout)
        inputPanel.add(userInputPanel);
        inputPanel.add(bookInputPanel);
        inputPanel.add(loanInputPanel);

        // ===== Display Areas (Books, Users, Loans) =====
        displayAreaBooks = new JTextArea(20, 30);
        displayAreaBooks.setEditable(false);
        JScrollPane scrollPaneBooks = new JScrollPane(displayAreaBooks);

        displayAreaUsers = new JTextArea(20, 30);
        displayAreaUsers.setEditable(false);
        JScrollPane scrollPaneUsers = new JScrollPane(displayAreaUsers);

        displayAreaLoans = new JTextArea(20, 30);
        displayAreaLoans.setEditable(false);
        JScrollPane scrollPaneLoans = new JScrollPane(displayAreaLoans);

        // Panels to hold each display area with titled borders
        JPanel booksDisplayPanel = new JPanel(new BorderLayout());
        booksDisplayPanel.setBorder(BorderFactory.createTitledBorder("Books"));
        booksDisplayPanel.add(scrollPaneBooks, BorderLayout.CENTER);

        JPanel usersDisplayPanel = new JPanel(new BorderLayout());
        usersDisplayPanel.setBorder(BorderFactory.createTitledBorder("Users"));
        usersDisplayPanel.add(scrollPaneUsers, BorderLayout.CENTER);

        JPanel loansDisplayPanel = new JPanel(new BorderLayout());
        loansDisplayPanel.setBorder(BorderFactory.createTitledBorder("Loans"));
        loansDisplayPanel.add(scrollPaneLoans, BorderLayout.CENTER);

        // Panel to hold all three display panels side by side
        JPanel displayPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(booksDisplayPanel);
        displayPanel.add(usersDisplayPanel);
        displayPanel.add(loansDisplayPanel);

        // ===== Add input panel to the top and display panel center =====
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(displayPanel, BorderLayout.CENTER);

        // Refresh all displays initially
        refreshBookDisplay();
        refreshUserDisplay();
        refreshLoanDisplay();

        frame.setVisible(true);
    }

    private void refreshBookDisplay() {
        displayAreaBooks.setText("=== Books in Library ===\n");
        for (Book book : bookService.getAllBooks()) {
            displayAreaBooks.append(book.toString() + "\n");
        }
    }

    private void refreshUserDisplay() {
        displayAreaUsers.setText("=== Users ===\n");
        for (User user : userService.getAllUsers()) {
            displayAreaUsers.append(user.toString() + "\n");
        }
    }

    private void refreshLoanDisplay() {
        displayAreaLoans.setText("=== Current Loans ===\n");
        for (Loan loan : loanService.getActiveLoans()) {
            displayAreaLoans.append(loan.toString() + "\n");
        }
}
}
