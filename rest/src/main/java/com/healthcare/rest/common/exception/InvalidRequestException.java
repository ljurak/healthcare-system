package com.healthcare.rest.common.exception;

import org.springframework.validation.Errors;

/**
 * Exception thrown when request body has validation errors.
 */
public class InvalidRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final Errors errors;
	
	public InvalidRequestException(String message, Errors errors) {
		super(message);
		this.errors = errors;
	}
	
	public Errors getErrors() {
		return errors;
	}
}
