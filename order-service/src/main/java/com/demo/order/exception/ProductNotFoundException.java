package com.demo.order.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int id;
	private String message = "Product not found with id : ";

	public ProductNotFoundException(int id) {
		this.id = id;
		this.message = message + id;
	}

	public ProductNotFoundException(int id, Throwable t) {
		super(t);
		this.id = id;
		this.message = message + id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
