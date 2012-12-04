package a10_token;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Word extends AToken implements TokenWord {
	String word;
	String lowercaseWord;

	public Word() {
		setToken("Blah1");
	}
	
	public Word(String input) {
		setToken(input);
	}
	
	private String toLowercase(String input) {
		return input.toLowerCase();
	}
	
	@SuppressWarnings("unused")
	private String[] toLowercase(String[] input) {
		String[] output = new String[input.length];
		for (int i = 0; i < output.length; i++) {
			output[i] = input[i].toLowerCase();
		}
		return output;
	}
	
	public void setToken(String input) {
		word = input;
		lowercaseWord = toLowercase(input);
	}
	
	public String getToken() {
		return word;
	}
	
	public String getLowercaseWord() {
		return lowercaseWord;
	}
}