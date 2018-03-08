package com.demo.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Error> handleNotFoundException(ProductNotFoundException ex) {
		Error error = new Error(ex.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
	}

}
