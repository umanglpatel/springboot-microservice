package com.demo.user.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int id;

	private String message = "User not found with id : ";

	public UserNotFoundException(int id) {
		super();
		this.id = id;
		message = message + id;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public int getId() {
		return id;
	}

}
