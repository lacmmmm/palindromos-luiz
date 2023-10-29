package com.desafio.palindromos.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class Constants {

	public static final String ERROR_MSG_PALINDROME_MIN_SIZE = "o palindromo deve ter no mínimo 3 caracteres";
	public static final String ERROR_MSG_PALINDROME_NOT_BLANK = "o palindromo não deve estar em branco";

	public static final String ERROR_MSG_PALINDROME_NOT_FOUND = "Não foi encontrado nenhuma registro com a palavra: ";
	public static final String ERROR_MSG_MATRIX_WITHOUT_PALINDROMES = "Não foi encontrado nenhum palíndromo na matriz informada";
	public static final String ERROR_MSG_INVALID_MATRIX = "A matriz de palíndromos informada é inválida";

}
