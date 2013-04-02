package tokenpackage;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;

@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class QuotedString extends AToken{
	
	public QuotedString(){
		genericstring = "empty";
	}
	
	public QuotedString(String newStartToken) {
		setTokenString(newStartToken); 
	} 
	

}
