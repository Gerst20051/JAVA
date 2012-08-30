package main;

import console.Console;

public class Assignment1 {
	public static void processString(String input) {
		final int MAX_SIZE = 50;
		final char space = ' ';
		String[] tokens = new String[MAX_SIZE];
		String bigtoken = "string";
		char token = ' ';
		int oindex = 0;
		int ctoken = 0;
		int index = 0;
		int strlen = input.length();
		if (0 == strlen) {
			System.out.println("Illegal String Length: " + strlen + ". Terminating program.");
			System.exit(-1);
		}
		System.out.println("Forwards:");
		while (index < strlen) {
			token = input.charAt(index);
			if (token == space || !(Character.isLetter(token)) || index == strlen-1) {
				if (index != strlen-1) {
					bigtoken = input.substring(oindex,index);
				} else {
					bigtoken = input.substring(oindex,index+1);
				}
				if (1 < index && input.charAt(index-1) != ' ' && Character.isLetter(input.charAt(index-1))) {
					tokens[ctoken++] = bigtoken;
					System.out.println(tokens[ctoken-1]);
				}
				oindex = index+1;
			}
			index++;
		}
		System.out.println("Backwards:");
		for (int i = ctoken-1; 0 <= i; i--) {
			System.out.println(tokens[i]);
		}
	}

	public static void main(String[] args) {
		String input;
		while (!(input = Console.readString()).equals(".")) {
			if (1 < input.length()) processString(input);
		}
	}
}