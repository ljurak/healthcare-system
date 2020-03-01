package com.healthcare.rest.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.healthcare.rest.doctor.exception.DoctorNotFoundException;
import com.healthcare.rest.doctor.exception.SpecialtyNotFoundException;
import com.healthcare.rest.patient.exception.PatientNotFoundException;
import com.healthcare.rest.user.exception.RefreshTokenException;
import com.healthcare.rest.visit.exception.VisitException;
import com.healthcare.rest.visit.exception.VisitNotFoundException;

@RestControllerAdvice(basePackages = {
	"com.healthcare.rest.doctor",
	"com.healthcare.rest.patient",
	"com.healthcare.rest.user",
	"com.healthcare.rest.visit"
})
@CrossOrigin
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({ 
		PatientNotFoundException.class, 
		DoctorNotFoundException.class, 
		VisitNotFoundException.class, 
		SpecialtyNotFoundException.class })
	public ResponseEntity<ApiError> handleEntityNotFoundException(RuntimeException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(VisitException.class)
	public ResponseEntity<ApiError> handleVisitException(VisitException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
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
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
		String message;
		if (ex instanceof BadCredentialsException) {
			message = "Incorrect username or password";
		} else {
			message = ex.getMessage();
		}
		
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, message);
		return new ResponseEntity<ApiError>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(RefreshTokenException.class)
	public ResponseEntity<ApiError> handleRefreshTokenException(RefreshTokenException ex) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println(ex.getMessage());
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Malformed JSON request");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
