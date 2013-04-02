package token;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Word {
	String word;
	String lowercaseWord;

	public Word() {
		setWord("Blah1");
	}
	
	public Word(String input) {
		setWord(input);
	}
	
	public String toLowercase(String input) {
		return input.toLowerCase();
	}
	
	public String[] toLowercase(String[] input) {
		String[] output = new String[input.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = input[i].toLowerCase();
		}
		return output;
	}
	
	public void setWord(String input) {
		word = input;
		lowercaseWord = toLowercase(input);
	}
	
	public String getWord() {
		return word;
	}
	
	public String getLowercaseWord() {
		return lowercaseWord;
	}
}