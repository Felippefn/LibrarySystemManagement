package com.system.library.service;

import com.system.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private static BookService instance;
    private List<Book> books = new ArrayList<>();

    // Singleton pattern
    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<Book> getAvailableBooks() {
        return books.stream()
                .filter(Book::getAvailable)
                .collect(Collectors.toList());
    }
    public Book getBookByTitle(String title) {
    	return books.stream().filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}
