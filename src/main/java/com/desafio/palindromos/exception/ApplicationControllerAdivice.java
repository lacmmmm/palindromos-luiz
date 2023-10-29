package com.desafio.palindromos.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ApplicationControllerAdivice {
	
	@ExceptionHandler(PalindromeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleValidationNotFound(PalindromeNotFoundException ex) {
        return ex.getMessage();
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public List<String> handleValidationErrors(ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage)
				.toList();
    }
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleInternalError(Exception ex) {
        return "Internal Server Error";
    }

}
