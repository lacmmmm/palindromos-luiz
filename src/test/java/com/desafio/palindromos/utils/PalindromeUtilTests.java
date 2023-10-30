package com.desafio.palindromos.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.desafio.palindromos.model.Palindrome;

class PalindromeUtilTests {

	@Test
	void getAllPalindromesFromMatrixWithPalindromesShouldReturnPalindromeList() {
		String matrix = """
				A O S S O A A
				Y R Z X L B R
				J R A P M C A
				J K A R Z D R
				Y L E D X E A
				Y L E R A R X
				Y L E R A R A
				""";
		  List<String> palindromeList = PalindromeUtil.getAllPalindromesFromMatrix(matrix)
				 .stream()
				 .map(Palindrome::getWord)
				 .toList();

		  assertTrue(palindromeList.contains("ARARA"));
		  assertTrue(palindromeList.contains("RADAR"));
	}
	
	@Test
	void getAllPalindromesFromMatrixWithoutPalindromesShouldReturnEmptyList() {
		String matrix = """
				QWERTY
				ASDFG
				ZXCVB
				""";
		  List<Palindrome> palindromeList = PalindromeUtil.getAllPalindromesFromMatrix(matrix);

		  assertTrue(palindromeList.isEmpty());
	}
}
