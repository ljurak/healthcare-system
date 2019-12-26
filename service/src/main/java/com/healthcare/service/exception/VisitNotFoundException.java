package com.healthcare.service.exception;

public class VisitNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public VisitNotFoundException(String message) {
		super(message);
	}
}
