package com.desafio.palindromos.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class MatrixValidator {
	public static boolean isValid(String matrix) {
		String[] rows = MatrixValidator.getRows(matrix);
		
		return MatrixValidator.hasntEmptyChar(matrix) 
				&& MatrixValidator.hasValidColumns(rows) 
				&& MatrixValidator.isPossibleToFindAPalindrome(rows);
	}
	
	private static boolean hasValidColumns(String[] rows) {
		
		if(!rows[0].trim().contains(" ")) {
			return false;
		}
		
		int numOfColumns = rows[0].trim().split(" ").length;
		
		for (int i = 1; i < rows.length; i++) {
			if(rows[i].trim().split(" ").length != numOfColumns) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean hasntEmptyChar(String matrix) {
		return !matrix.contains("  ");
	}
	
	private static boolean isPossibleToFindAPalindrome(String[] validMatrixRows) {
		int numOfRows = validMatrixRows.length;
		int numOfColumns = validMatrixRows[0].split(" ").length;
		
		return numOfRows >=3 || numOfColumns >= 3;
	}
	
	private static String[] getRows(String matrix){
		String matrixWithoutWhiteSpaces = matrix.trim();
		return matrixWithoutWhiteSpaces.split("\n");
	}

}
