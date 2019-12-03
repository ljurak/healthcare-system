package com.healthcare.service.exception;

public class PatientException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PatientException(String message) {
		super(message);
	}
}
