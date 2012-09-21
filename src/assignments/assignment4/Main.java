package assignment4;

import console.Console;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;

public class Main {
	static String input;
	
	public static void main(String[] args) {
		a4_scanner.Scanner scanner = new a4_scanner.Scanner("Hey");
		while (!((input = Console.readString()).charAt(0) == '.')) {
			// MoVe 050 {saY “hi!”}
			// MoVe 050 {saY "hi!"}
			if (1 < input.length()) {
				scanner.setString(input);
			}
		}
	}
}