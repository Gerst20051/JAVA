package tokenpackage;

public class AToken implements GeneralTokenInterface {
	String genericstring;
	int number;
	
	public String getTokenString() {
		return genericstring;
	}
	
	public void setTokenString(String newgenericstring) {
		genericstring = newgenericstring;
	}
	
}