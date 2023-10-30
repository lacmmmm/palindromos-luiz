package com.desafio.palindromos;

import java.util.List;

import com.desafio.palindromos.model.Palindrome;
import com.desafio.palindromos.model.PalindromeRequest;

public class PalindromeTestsUtil {
	public static final String INVALID_MATRIX = """
			A O
			Y R Z X L B
			J S 
			J K P R Z D
			Y
			""";

	public static final String MATRIX_WITH_EMPTY_CHAR = """
			A O C
			Y   Z
			""";

	public static final String MATRIX_WITHOUT_3ROWS_OR_3COLUMNS = """
			A O
			A O
			""";

	public static PalindromeRequest getPalindromeRequestEntity(List<String> wordlist, PalindromeRequest emptyRequest){
		List<Palindrome> palindromesList = wordlist.stream()
				.map(word -> {				
					Palindrome palindrome = new Palindrome();
					palindrome.setWord(word);
					palindrome.setRequest(emptyRequest);
					return palindrome;
				})
				.toList();

		emptyRequest.setPalindromes(palindromesList);

		return emptyRequest;
	}


}
