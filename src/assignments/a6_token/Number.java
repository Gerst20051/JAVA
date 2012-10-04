package a6_token;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Number extends AToken implements TokenNumber {
	String stringNumber;
	int number;

	public Number() {
		setToken("00200");
	}
	
	public Number(String input) {
		setToken(input);
	}
	
	public void setToken(String input) {
		stringNumber = input;
		number = Integer.parseInt(input);
	}
	
	public String getToken() {
		return stringNumber;
	}
	
	public int getNumber() {
		return number;
	}
}