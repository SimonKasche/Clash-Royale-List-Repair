package me.Stun.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class FileManagement {

	public static String[][] readPreviousMatchesArray() throws IOException {
		int lines = countLines("matches.txt") - 1;

		String[][] readMatches = new String[lines][9];
		BufferedReader br = new BufferedReader(new FileReader("matches.txt"));

		String[] deck = new String[9];

		for (int i = 0; i < lines; i++) {
			String line = br.readLine();
			System.out.println("reading match: " + i);
			if (line == null || line.equals("") || line.equals(" ")) {
				// idfk
			} else {
				deck = line.split(" , ");
				if (deck != null && deck.length > 9)
					readMatches[i] = (deck);
			}
		}

		br.close();
		return readMatches;

	}

	public static void writeArray(String[][] array) throws IOException {

		System.out.print("\nwriting array to matches.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("matches.txt"));

		boolean newline = true;

		for (int i = 0; i < array.length; i++) {

			for (int j = 0; j < array[i].length; j++) {

				if (array[i] != null && array[i][j] != null) {

					if (j == array[i].length - 1) {

						bw.write(array[i][j]);

					} else {

						bw.write(array[i][j] + " , ");

					}

					newline = true;

				} else {
					newline = false;
				}

			}
			if (newline == true) {

				bw.newLine();
			}

		}

		System.out.println("\tdone");

		bw.close();

	}

	public static int countLines(String path) throws IOException {
		FileReader input = new FileReader(path);
		LineNumberReader count = new LineNumberReader(input);

		int lines = (int) count.lines().count() + 1;
		count.close();

		return lines;
	}

}
