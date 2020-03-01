package com.healthcare.rest.doctor.exception;

public class SpecialtyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SpecialtyNotFoundException(String message) {
		super(message);
	}
}
