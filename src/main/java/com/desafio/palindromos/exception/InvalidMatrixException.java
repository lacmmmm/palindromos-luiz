package com.desafio.palindromos.exception;

import com.desafio.palindromos.utils.Constants;

public class InvalidMatrixException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidMatrixException() {
		super(Constants.ERROR_MSG_INVALID_MATRIX);
	}

}
