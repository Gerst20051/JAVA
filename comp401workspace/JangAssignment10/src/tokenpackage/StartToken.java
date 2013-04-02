package tokenpackage;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class StartToken extends AToken{
	
	public StartToken(){
		genericstring = "empty";
	}
	
	public StartToken(String newStartToken) {
		setTokenString(newStartToken); 
	} 

}
