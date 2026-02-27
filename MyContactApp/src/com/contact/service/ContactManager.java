package com.contact.service;
import java.util.ArrayList;
import java.util.List;
import com.contact.user.FreeUser;
import com.contact.user.PremiumUser;
import com.contact.user.User;

public class ContactManager {

	private List<User> users = new ArrayList<>();


	public void registerUser(int type, String name, String email, String password) {


		if (!isValidEmail(email)) {
			System.out.println("Invalid Email Format!");
			return;
		}


		if (password.length() < 4) {
			System.out.println("Password must be at least 4 characters!");
			return;
		}


		for (User u : users) {
			if (u.getEmail().equalsIgnoreCase(email)) {
				System.out.println("User already exists with this email!");
				return;
			}
		}

		User newUser;

		if (type == 1) {
			newUser = new FreeUser(name, email, password);
		} else {
			newUser = new PremiumUser(name, email, password);
		}

		users.add(newUser);

		System.out.println(newUser.getUserType() + " Registered Successfully!");
	}
	public User login(String email, String password) {

	    for (User u : users) {
	        if (u.getEmail().equalsIgnoreCase(email) &&
	            u.checkPassword(password)) {

	            System.out.println("Login Successful!");
	            return u;
	        }
	    }

	    System.out.println("Invalid Credentials!");
	    return null;
	}


	private boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		return email.matches(emailRegex);
	}

	public List<User> getUsers() {
		return users;
	}
}
