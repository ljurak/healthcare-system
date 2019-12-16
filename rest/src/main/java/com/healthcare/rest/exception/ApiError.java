package com.healthcare.rest.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	
	private String message;
	
	private HttpStatus status;
	
	private List<FieldValidationError> fieldErrors;
	
	private ApiError() {
		this.timestamp = LocalDateTime.now();
	}
	
	public ApiError(HttpStatus status) {
		this();
		this.status = status;
		this.message = "Unexpected error occured";
	}
	
	public ApiError(HttpStatus status, String message) {
		this();
		this.status = status;
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public List<FieldValidationError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldValidationError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}	
}
