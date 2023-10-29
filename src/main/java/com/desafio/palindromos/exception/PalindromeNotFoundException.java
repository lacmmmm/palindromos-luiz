package com.desafio.palindromos.exception;

import com.desafio.palindromos.utils.Constants;

public class PalindromeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PalindromeNotFoundException(String palindrome) {
		super(Constants.ERROR_MSG_PALINDROME_NOT_FOUND + palindrome);
	}
	
	public PalindromeNotFoundException() {
		super(Constants.ERROR_MSG_MATRIX_WITHOUT_PALINDROMES);
	}

}
