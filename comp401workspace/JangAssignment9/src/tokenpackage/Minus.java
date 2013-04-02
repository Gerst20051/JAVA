package tokenpackage;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Minus extends AToken {

	public Minus(){
		genericstring = "empty";
	}
	
	public Minus(String newMinusToken) {
		setTokenString(newMinusToken); 
	} 
	
}
