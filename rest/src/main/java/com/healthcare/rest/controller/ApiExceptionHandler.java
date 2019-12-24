package com.healthcare.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.healthcare.rest.exception.ApiError;
import com.healthcare.rest.exception.FieldValidationError;
import com.healthcare.rest.exception.InvalidRequestException;
import com.healthcare.service.exception.DoctorNotFoundException;
import com.healthcare.service.exception.PatientNotFoundException;

@RestControllerAdvice
@CrossOrigin
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ PatientNotFoundException.class, DoctorNotFoundException.class })
	public ResponseEntity<ApiError> handlePatientNotFoundException(RuntimeException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<ApiError> handleInvalidRequestException(InvalidRequestException ex) {
		List<FieldValidationError> fieldValidationErrors = new ArrayList<>();
		
		List<FieldError> fieldErrors = ex.getErrors().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			String message = fieldError.getDefaultMessage();
			FieldValidationError error = new FieldValidationError(
				fieldError.getField(), 
				fieldError.getRejectedValue(),
				message
			);
			fieldValidationErrors.add(error);
		}
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
		apiError.setFieldErrors(fieldValidationErrors);
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Malformed JSON request");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
