package tokenpackage;

public abstract class AToken implements Token {
	
	String genericstring;
	int number;
	
	public String getTokenString() {
		return genericstring;
	}
	
	public void setTokenString(String newgenericstring) {
		genericstring = newgenericstring;
	}
	
}