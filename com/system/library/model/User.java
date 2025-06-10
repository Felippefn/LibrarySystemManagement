package com.system.library.model;

import java.util.regex.Pattern;

public class User {

	private static int incrementId = 1;
	
	private int id;
	private String name;
	private String lastName;
	private String email;
	
	public User(String name, String lastName, String email) {
		this.id = incrementId++;
		this.setName(name);
		this.setLastName(lastName);
		this.setEmail(email);
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		email = email.toLowerCase();
		if (isValidEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("E-mail inválido: " + email);
        }
	}
	@Override
    public String toString() {
        return  "Id: " + id +"\nName: "+ name + "\nLast Name: " + lastName + "\nEmail: " + email;
	}
	
	private boolean isValidEmail(String email) {
	        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
	        return Pattern.matches(regex, email);
	    }
}
