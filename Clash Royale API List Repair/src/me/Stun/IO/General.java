package me.Stun.IO;

import java.util.LinkedList;

public class General {

	public static int[] listToArray(LinkedList<Integer> list) {

		int[] output = new int[list.size()];

		for (int i = 0; i < output.length; i++) {

			output[i] = list.get(i);

			System.out.println("converting list to array: " + i + "/" + output.length);

		}

		return output;

	}
}
