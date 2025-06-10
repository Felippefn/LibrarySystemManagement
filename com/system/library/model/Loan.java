package com.system.library.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private static int idCounter = 1;
    private int id;
    private User user;
    private Book book;
    private LocalDate dateBorrow;
    private LocalDate dateReturn;

    public Loan(User user, Book book, LocalDate dateBorrow, int daysToReturn) {
        if (dateBorrow.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Borrow date cannot be in the past.");
        }

        this.id = idCounter++;
        this.setUser(user);
        this.setBook(book);
        this.setDateBorrow(dateBorrow);
        this.setDateReturn(dateBorrow.plusDays(daysToReturn));
    }

    // ========= Getters =========
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDateBorrow() {
        return dateBorrow;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    // ========= Setters =========
    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
        if (book != null) {
            book.setAvailable(false); // mark as borrowed
        }
    }

    public void setDateBorrow(LocalDate dateBorrow) {
        if (dateBorrow.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Borrow date cannot be in the past.");
        }
        this.dateBorrow = dateBorrow;
    }

    public void setDateReturn(LocalDate dateReturn) {
        if (dateReturn.isBefore(this.dateBorrow)) {
            throw new IllegalArgumentException("Return date cannot be before borrow date.");
        }
        this.dateReturn = dateReturn;
    }

    // ========= Utility =========
    public void alertToReturn() {
        if (dateReturn == null) {
            System.out.println("No return date set.");
            return;
        }

        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), dateReturn);

        if (daysLeft > 0) {
            System.out.println("You need to return the book in " + daysLeft + " day(s).");
        } else if (daysLeft == 0) {
            System.out.println("The book is due today!");
        } else {
            System.out.println("The book is overdue by " + Math.abs(daysLeft) + " day(s).");
        }
    }

    public void returnBook() {
        if (book != null) {
            book.setAvailable(true); // Mark the book as available again
        }
        System.out.println("Book returned successfully.");
    }
    
    @Override
    public String toString() {
        return "Loan {" +
                "ID = " + id +
                ", User = " + (user != null ? user.getName() + " " + user.getLastName() : "Unknown") +
                ", Book = " + (book != null ? book.getTitle() : "Unknown") +
                ", Borrow Date = " + (dateBorrow != null ? dateBorrow : "N/A") +
                ", Return Date = " + (dateReturn != null ? dateReturn : "N/A") +
                '}';
    }
}
