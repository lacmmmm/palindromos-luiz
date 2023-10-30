package com.desafio.palindromos.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.desafio.palindromos.helper.PalindromeTestsHelper;

class MatrixUtilTests {
	
	@Test
	void getAllPharesFromMatrixWith1ColumnShouldReturn1Phases() {
		List<String> phrases = MatrixUtil.getAllPharesWithAtLeast3Chars(PalindromeTestsHelper.VALID_MATRIX_WITH_1_COLUMN);

		assertEquals(1, phrases.size());
	}
	
	@Test
	void getAllPharesFromMatrixWith1RowShouldReturn1Phases() {
		List<String> phrases = MatrixUtil.getAllPharesWithAtLeast3Chars(PalindromeTestsHelper.VALID_MATRIX_WITH_1_ROW);

		assertEquals(1, phrases.size());
	}
	
	@Test
	void getAllPharesFromMatrixWithMoreRowsThanColumnsShouldReturnAllPhases() {
		List<String> phrases = MatrixUtil.getAllPharesWithAtLeast3Chars(PalindromeTestsHelper.VALID_MATRIX_WITH_MORE_ROWS_THAN_COLUMNS);

		int numRows = 4;
		int numColums = 3;
		int numDiagonalsFromLeft = 2;
		int numDiagonalsFromRight = 2;
		
		int totalPhrases = numRows + numColums + numDiagonalsFromLeft + numDiagonalsFromRight;
		
		assertEquals(totalPhrases, phrases.size());
	}
	
	@Test
	void getAllPharesFromMatrixWithMoreColumnsThanRowsShouldReturnAllPhases() {
		List<String> phrases = MatrixUtil.getAllPharesWithAtLeast3Chars(PalindromeTestsHelper.VALID_MATRIX_WITH_MORE_COLUMNS_THAN_ROWS);

		int numRows = 3;
		int numColums = 4;
		int numDiagonalsFromLeft = 2;
		int numDiagonalsFromRight = 2;
		
		int totalPhrases = numRows + numColums + numDiagonalsFromLeft + numDiagonalsFromRight;
		
		assertEquals(totalPhrases, phrases.size());
	}

}
