package com.desafio.palindromos.utils;

import java.util.ArrayList;
import java.util.List;

import com.desafio.palindromos.model.Palindrome;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class PalindromeUtil {

	public static List<Palindrome> getAllPalindromesFromMatrix(String matrix) {
		List<Palindrome> palindromesList = new ArrayList<>();
		
		if(!MatrixValidator.isValid(matrix)) {
			return palindromesList;
		}
		
		List<String> phrases = MatrixUtil.getAllPharesWithAtLeast3Chars(matrix);
		
		if(phrases.isEmpty()) {
			return palindromesList;
		}

		for (String phrase : phrases) {
			String phraseEntry = phrase;

			while(phraseEntry.length() >= 3) {
				String firstPalindrome = getFirstPalindrome(phraseEntry);

				if (firstPalindrome == null) {
					break;
				} else {
					Palindrome p =  new Palindrome();
					p.setWord(firstPalindrome);
					palindromesList.add(p);
					phraseEntry = phraseEntry.substring(phraseEntry.indexOf(firstPalindrome)+ firstPalindrome.length());
				}
			}
		}

		return palindromesList;
	}

	private static String getFirstPalindrome(String entry){
		String currentEntry = entry;

		while(currentEntry.length() >= 3) {
			String last2Chars = getLast2Chars(currentEntry);
			int lastIndex = findLastIndexFrom(currentEntry, last2Chars, 1);

			if(lastIndex < 0) {
				currentEntry = currentEntry.substring(1);
			}

			while(lastIndex > 0){
				String word = currentEntry.substring(0, lastIndex + 2);

				if(isPalindrome(word)){
					return word;
				}
				else {
					lastIndex = currentEntry.substring(0, lastIndex - 2).lastIndexOf(last2Chars);
					if(lastIndex < 0) {
						currentEntry = currentEntry.substring(1);
					}
				}
			}
		}
		return null;
	}

	private static boolean isPalindrome(String word) {

		String nextInitialChar = word.charAt (0) + "";
		String nextFinalChar = word.charAt (word.length() - 1) + "";


		if(nextInitialChar.equals(nextFinalChar)) {
			if((word.length()-2) <= 1) {
				return true;
			}
			else {
				return isPalindrome(word.substring(1, word.length() - 1));
			}
		}

		return false;
	}

	private static String getLast2Chars(String currentEntry) {
		String firstChar = currentEntry.charAt(0) + "";
		String secondChar = currentEntry.charAt(1) + "";

		return secondChar + firstChar;
	}
	
	private static int findLastIndexFrom(String text, String subString, int index) {
		text = text.substring(index);
		int lastIndex = text.lastIndexOf(subString);
		
		if(lastIndex >= 0) {
			lastIndex++;
		}
		
		return lastIndex;
	}
}
