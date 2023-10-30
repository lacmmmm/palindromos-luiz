package com.desafio.palindromos.helper;

import java.util.List;

import com.desafio.palindromos.model.Palindrome;
import com.desafio.palindromos.model.PalindromeRequest;

public class PalindromeTestsHelper {
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

	public static final String VALID_MATRIX_WITH_1_COLUMN = """
			0
			1
			1
			""";

	public static final String VALID_MATRIX_WITH_1_ROW = "0 1 2 3 4";
	
	public static final String VALID_MATRIX_WITH_MORE_COLUMNS_THAN_ROWS = """
			0 1 1 0
			A B B A
			0 1 1 0
			""";

	public static final String VALID_MATRIX_WITH_MORE_ROWS_THAN_COLUMNS = """
			0 A 0
			1 B 1
			1 B 1
			0 A 0
			""";

	public static PalindromeRequest getPalindromeRequestEntity(List<String> wordlist, PalindromeRequest request){
		List<Palindrome> palindromesList = wordlist.stream()
				.map(word -> {				
					Palindrome palindrome = new Palindrome();
					palindrome.setWord(word);
					palindrome.setRequest(request);
					return palindrome;
				})
				.toList();

		request.setPalindromes(palindromesList);

		return request;
	}


}
