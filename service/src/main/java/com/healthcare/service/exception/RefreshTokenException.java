package com.healthcare.service.exception;

public class RefreshTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RefreshTokenException(String message) {
		super(message);
	}
}
