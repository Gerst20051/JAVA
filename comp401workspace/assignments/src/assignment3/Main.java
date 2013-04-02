package assignment3;

import console.Console;
import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;

public class Main {
	static String input;
	
	public static void main(String[] args) {
		scanner.Scanner scanner = new scanner.Scanner("Hey");
		token.Word word = new token.Word("Word");
		token.Number number = new token.Number("00200");
		ObjectEditor.setDefaultAttribute(AttributeNames.COMPONENT_WIDTH, 800);
		ObjectEditor.edit(scanner);
		ObjectEditor.edit(word);
		ObjectEditor.edit(number);
		while (!((input = Console.readString()).charAt(0) == '.')) {
			// MoVe 050 {saY “hi!”}
			// MoVe 050 {saY "hi!"}
			if (1 < input.length()) {
				scanner.setString(input);
			}
		}
	}
}