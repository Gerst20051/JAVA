package tokenpackage;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Number extends AToken implements NumberTokenInterface{
	
	public Number() {
		genericstring = "empty";
	}
	public Number(String newInitialNumberString){
		setTokenString(newInitialNumberString);
	}
	
	private int number;
	
	public int getReadonlyProperty(){
		return number;
	}
	
	public void setTokenString(String newNumberString){
		genericstring = newNumberString;
		number = Integer.parseInt(genericstring);
	}
}
