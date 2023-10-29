package com.desafio.palindromos.exception;

public class PalindromeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PalindromeNotFoundException(String palindrome) {
		super("Não foi encontrado nenhuma requisição com a palavra: " + palindrome);
	}
	
	public PalindromeNotFoundException() {
		super("Não foi encontrado nenhum palíndromo na matriz informada");
	}

}
