package com.desafio.palindromos.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MatrizUtil {

	protected List<String> getAllPhares(@NotBlank @NotNull String matrix) {
		String[][] matrixArray = this.getMatriz(matrix);
		List<String> lines = this.getLines(matrix);
		List<String> columns = this.getColumns(matrixArray);
		List<String> diagonalsFromLeft = this.getDiagonalsFromLeft(matrixArray);
		List<String> diagonalsFromRight = this.getDiagonalsFromRight(matrixArray);

		return  Stream.of(lines, columns, diagonalsFromLeft, diagonalsFromRight)
				.flatMap(Collection::stream)
				.toList();
	}

	private String[][] getMatriz(String textLines) {
		String[] lines = textLines.split("\n");

		int qtLines = lines.length;
		int qtColumns = lines[0].trim().split(" ").length;

		String[][] matrix = new String[qtLines][qtColumns];

		for (int i = 0; i < qtLines; i++) {
			matrix[i] = lines[i].trim().split(" ");
		}

		return matrix;
	}

	private List<String> getLines(String textLines) {
		String[] lines = textLines.split("\n");
		return List.of(lines).stream()
				.map(line -> line.replace(" ", "").trim())
				.toList();
	}

	private List<String> getColumns(String[][] matrix) {
		String[] retorno = new String[matrix[0].length];
		Arrays.fill(retorno, "");

		for (int line = 0; line < matrix.length; line++) {
			for (int col = 0; col < retorno.length; col++) {
				retorno[col] += matrix[line][col];
			}
		}

		return List.of(retorno);
	}

	private List<String> getDiagonalsFromLeft(String[][] matrix) {
		ArrayList<String> retorno = new ArrayList<>();
		int qtLines = matrix.length;
		int qtCols = matrix[0].length;

		int line = 0;
		int initialColumn = qtCols-3;
		int initialLine = 0;
		int column;

		while (initialLine < qtLines-3) {

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

			retorno.add(diagonal);
		}

		return retorno;
	}

	private List<String> getDiagonalsFromRight(String[][] matrix) {
		ArrayList<String> retorno = new ArrayList<>();
		int qtLines = matrix.length;
		int qtCols = matrix[0].length;

		int line = 0;
		int initialColumn = 2;
		int initialLine = 0;
		int column;

		while (initialLine < qtLines-3) {

			if( initialColumn == qtCols) {
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

			retorno.add(diagonal);
		}
		
		return retorno;
	}

}
