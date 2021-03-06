package me.Stun.Repair;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class MainProcess {

	public static String[][] matches;
	public static LinkedList<Integer> duplicateIndex = new LinkedList<Integer>();
	public static int duplicateCount = 0;

	private static final DecimalFormat df = new DecimalFormat("0.00");

	public static void repair() {

		int steps = matches.length / 1000;

		double estimation = 0;
		Long startEstimation = 0L;
		Long endEstimation = 0L;

		for (int i = 0; i < matches.length; i++) {

			if (i % 1000 == 0) {
				endEstimation = System.nanoTime();

				estimation = (double) (endEstimation - startEstimation) / 6e+10 * steps;
				steps--;

				String temp = df.format(estimation);
				int index = 0;
				for (int k = 0; k < temp.length(); k++) {
					if (temp.charAt(k) == ',')
						index = k;
				}
				StringBuilder sb = new StringBuilder(temp);
				sb.setCharAt(index, '.');

				estimation = Double.parseDouble(sb.toString());

				startEstimation = System.nanoTime();
			}

			Long start = System.nanoTime();

			for (int j = i + 1; j < matches.length; j++) {

				try {
					if (matches[i][8].equals(matches[j][8]) && matches[i][11].equals(matches[j][11])
							&& duplicateIndex.contains(j) == false) {

						duplicateIndex.add(j);
						duplicateCount++;

					}
				} catch (Exception e) {
					//System.out.println("null value");
				}

			}

			Long end = System.nanoTime();
			double resultSeconds = end - start;

			System.out.println("analyzing: " + i + "/" + matches.length + "\tduplicates.size() = " + duplicateCount
					+ "\ttime/match: " + df.format(resultSeconds / 1000000) + "ms\testimated time remaining: "
					+ estimation + "min");

		}

	}

	public static void removeDuplicates() throws IOException {

		String[][] newMatches = new String[matches.length][matches[0].length];
		int[] duplicatesArray = me.Stun.IO.General.listToArray(duplicateIndex);
		boolean found = false;

		for (int i = 0; i < matches.length; i++) {

			for (int j = 0; j < duplicatesArray.length; j++) {

				if (i == duplicatesArray[j]) {

					found = true;

				}

			}

			if (found == false) {

				newMatches[i] = matches[i];

			} else {

				found = false;

			}

			System.out.println("removing duplicates: " + i + "/" + matches.length);

		}

		me.Stun.IO.FileManagement.writeArray(newMatches);

	}

}
