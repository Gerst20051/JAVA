package tokenpackage;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Plus extends AToken{
	

	public Plus(){
		genericstring = "empty";
	}
	
	public Plus(String newPlusToken) {
		setTokenString(newPlusToken); 
	} 
	
}
