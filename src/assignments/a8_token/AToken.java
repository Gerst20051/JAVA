package a8_token;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class AToken implements Token {
	String token;
	
	public void setToken(String input) {
		token = input;
	}
	
	public String getToken() {
		return token;
	}
}