package token;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Number {
	String stringNumber;
	int number;

	public Number() {
		setStringNumber("00200");
	}
	
	public Number(String input) {
		setStringNumber(input);
	}
	
	public void setStringNumber(String input) {
		stringNumber = input;
		number = Integer.parseInt(input);
	}
	
	public String getStringNumber() {
		return stringNumber;
	}
	
	public int getNumber() {
		return number;
	}
}