/**
 * 
 */
package com.system.library.model;

import com.system.library.service.BookService;

/**
 * 
 */
public class Book {
	private static int incrementId = 1;
	
	private int id;
	private String title, author, genre;
	private Boolean available;
	
	public Book(String title, String  author, String genre, Boolean available) {
		this.setId(incrementId++);
		this.setTitle(title);
		this.setAuthor(author);
		this.setGenre(genre);
		this.available = available;
		
		// Automatically add to the book list
        BookService.getInstance().addBook(this);
	}
	
	
	
	public int getId() {return id;}
	public String getTitle() {return title;}
	public String getAuthor() {return author;}
	public String getGenre() {return genre;}
	public Boolean getAvailable() {return available;}
	
	
	public void setId(int id) {this.id = id;}
	public void setTitle(String title) {this.title = title;}
	public void setAuthor(String author) {this.author = author;}
	public void setGenre(String genre) {
		genre = genre.toLowerCase();
		this.genre = genre;}
	public void setAvailable(Boolean available) {this.available = available;}
	
	
	@Override
    public String toString() {
		return "Book: " + getId() +
		           "\nTitle: " + getTitle() +
		           "\ngenre: " + getGenre() +
		           "\nAvailable: " + (getAvailable() ? "Yes" : "No");
    }
}
