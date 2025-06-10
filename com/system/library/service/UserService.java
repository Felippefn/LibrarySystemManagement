package com.system.library.service;

import java.util.ArrayList;
import java.util.List;

import com.system.library.model.User;

public class UserService {
	private List<User> users = new ArrayList<>();
	private static UserService instance;

	public static UserService getInstance() {
		if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
	
	public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users;
    }
    
    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

}
