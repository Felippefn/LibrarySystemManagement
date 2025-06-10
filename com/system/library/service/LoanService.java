package com.system.library.service;

import com.system.library.model.Book;
import com.system.library.model.Loan;
import com.system.library.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanService {
    private List<Loan> loans = new ArrayList<>();

    // Create a new loan with a duration in days
    public Loan createLoan(User user, Book book, int daysToReturn) {
        if (!book.getAvailable()) {
            throw new IllegalStateException("Book is already loaned out.");
        }

        LocalDate today = LocalDate.now();
        Loan loan = new Loan(user, book, today, daysToReturn);
        loans.add(loan);
        return loan;
    }

    // Return a book and mark as available
    public void returnBook(Loan loan) {
        loan.returnBook();
    }

    // List all active loans (books currently borrowed)
    public List<Loan> getActiveLoans() {
        List<Loan> active = new ArrayList<>();
        for (Loan loan : loans) {
            if (!loan.getBook().getAvailable()) {
                active.add(loan);
            }
        }
        return active;
    }
    
    public List<Loan> getLoansFromUser(String name){
    	List<Loan> user = new ArrayList<>();
    	for (Loan loan : loans) {
    		if(loan.getUser().getName()==name) {
    			user.add(loan);
    		}
    	}
		return user;
    }

    // List all loans by a specific user
    public List<Loan> getLoansByUser(User user) {
        List<Loan> userLoans = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.getUser().equals(user)) {
                userLoans.add(loan);
            }
        }
        return userLoans;
    }
}
