package tokenpackage;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class EndToken extends AToken {

	
	public EndToken(){
		genericstring = "empty";
	}
	
	public EndToken(String newEndToken) {
		setTokenString(newEndToken); 
	} 
	
}
