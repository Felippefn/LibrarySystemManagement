package com.system.library;

import com.system.library.service.*;


public class Main {
    public static void main(String[] args) {
    	
    	BookService bookService = new BookService();
        UserService userService = new UserService();
        LoanService loanService = new LoanService();

        new LibraryUI(bookService, userService, loanService);
    }
}
