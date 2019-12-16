package com.healthcare.rest.exception;

public class FieldValidationError {
	
	private String field;
	
	private Object incorrectValue;
	
	private String message;

	public FieldValidationError(String field, Object incorrectValue, String message) {
		this.field = field;
		this.incorrectValue = incorrectValue;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public Object getIncorrectValue() {
		return incorrectValue;
	}

	public String getMessage() {
		return message;
	}	
}
