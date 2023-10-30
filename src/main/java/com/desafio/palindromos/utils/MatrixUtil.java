package com.desafio.palindromos.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class MatrixUtil {

	protected static List<String> getAllPharesWithAtLeast3Chars(@NotBlank @NotNull String matrix) {
		String[][] matrixArray = getMatriz(matrix);
		List<String> rows = getRows(matrix);
		List<String> columns = getColumns(matrixArray);
		List<String> diagonalsFromLeft = getDiagonalsFromLeft(matrixArray);
		List<String> diagonalsFromRight = getDiagonalsFromRight(matrixArray);

		return  Stream.of(rows, columns, diagonalsFromLeft, diagonalsFromRight)
				.flatMap(Collection::stream)
				.toList();
	}

	private static String[][] getMatriz(String textLines) {
		String[] lines = textLines.split("\n");

		int qtLines = lines.length;
		int qtColumns = lines[0].trim().split(" ").length;

		String[][] matrix = new String[qtLines][qtColumns];

		for (int i = 0; i < qtLines; i++) {
			matrix[i] = lines[i].trim().split(" ");
		}

		return matrix;
	}

	private static List<String> getRows(String textLines) {
		String[] lines = textLines.split("\n");
		return List.of(lines).stream()
				.map(line -> line.replace(" ", "").trim())
				.filter(line -> line.length() > 2)
				.toList();
	}

	private static List<String> getColumns(String[][] matrix) {
		String[] retorno = new String[matrix[0].length];
		Arrays.fill(retorno, "");

		for (int line = 0; line < matrix.length; line++) {
			for (int col = 0; col < retorno.length; col++) {
				retorno[col] += matrix[line][col];
			}
		}

		return List.of(retorno).stream().filter(column -> column.length() > 2).toList();
	}

	private static List<String> getDiagonalsFromLeft(String[][] matrix) {
		ArrayList<String> retorno = new ArrayList<>();
		int qtLines = matrix.length;
		int qtCols = matrix[0].length;

		int line = 0;
		int initialColumn = qtCols-3;
		int initialLine = 0;
		int column;

		while (initialLine <= qtLines-3 && initialLine < qtLines && initialColumn < qtCols) {

			if( initialColumn < 0) {
				column = 0;
				initialLine++;
			}
			else {
				column = initialColumn--;
			}

			line = initialLine;


			String diagonal = "";
			while(column < qtCols && line < qtLines) {
				diagonal += matrix[line++][column++];
			}

			if(diagonal.length() > 2) {
				retorno.add(diagonal);
			}
		}

		return retorno;
	}

	private static List<String> getDiagonalsFromRight(String[][] matrix) {
		ArrayList<String> retorno = new ArrayList<>();
		int qtLines = matrix.length;
		int qtCols = matrix[0].length;

		int line = 0;
		int initialColumn = 2;
		int initialLine = 0;
		int column;

		while (initialLine <= qtLines-3 && initialLine < qtLines) {

			if( initialColumn >= qtCols) {
				column = qtCols-1;
				initialLine++;
			}
			else {
				column = initialColumn++;
			}

			line = initialLine;

			String diagonal = "";
			while(column >= 0 && line < qtLines) {
				diagonal += matrix[line++][column--];
			}

			if(diagonal.length() > 2) {
				retorno.add(diagonal);
			}
		}

		return retorno;
	}

	public static void main(String[] args) {
		String VALID_MATRIX_WITH_MORE_ROWS_THAN_COLUMNS = """
				0 A 9
				1 B 6
				2 C 3
				3 D 5
				""";
		String[][] matrixArray = getMatriz(VALID_MATRIX_WITH_MORE_ROWS_THAN_COLUMNS);
		System.out.println(getDiagonalsFromRight(matrixArray));
	}

}
