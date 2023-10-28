package com.desafio.palindromos.dto;

import java.util.List;

public record PalindromeRequestDTO (
		Long id, List<String> palindrimes){
}