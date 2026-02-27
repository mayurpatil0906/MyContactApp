package com.contact.user;

public class FreeUser extends User {
	public FreeUser(String name, String email, String password) {
		super(name, email, password);
	}

	@Override
	public String getUserType() {
		return "Free User";
	}
}


