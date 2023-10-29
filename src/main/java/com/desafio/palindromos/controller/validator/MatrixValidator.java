package com.desafio.palindromos.controller.validator;

public class MatrixValidator {
	public static boolean isValid(String matrix) {
		return MatrixValidator.hasntEmptyChar(matrix) 
				&& MatrixValidator.hasValidNumsOfColumns(matrix) 
				&& MatrixValidator.isPossibleToFindAPalindrome(matrix);
	}
	
	private static boolean hasValidNumsOfColumns(String matrix) {
		String[] rows = MatrixValidator.getRows(matrix);
		
		int numOfColumns = rows[0].length();
		
		for (int i = 1; i < rows.length; i++) {
			if(rows[i].length() != numOfColumns) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean hasntEmptyChar(String matrix) {
		return !matrix.contains("  ");
	}
	
	private static boolean isPossibleToFindAPalindrome(String validMatrix) {
		String[] rows = MatrixValidator.getRows(validMatrix);

		int numOfRows = rows.length;
		int numOfColumns = rows[0].length();
		
		return numOfRows >=3 || numOfColumns >= 3;
	}
	
	private static String[] getRows(String matrix){
		String matrixWithoutWhiteSpaces = matrix.trim().replace(" ", "");
		return matrixWithoutWhiteSpaces.split("\n");
	}

}
