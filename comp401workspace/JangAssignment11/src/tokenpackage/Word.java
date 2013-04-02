package tokenpackage;

import java.lang.String;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Word extends AToken implements WordTokenInterface {
	private String lowerword;
	
	public Word() {
		setTokenString("EMPTY");
	}

	public Word(String initialwordstring) {
		setTokenString(initialwordstring);
	}

	public String getReadonlyProperty() {
		return lowerword;
	}

	public void setTokenString(String newWordString) {
		genericstring = newWordString;
		lowerword = genericstring.toLowerCase();
	}
}
