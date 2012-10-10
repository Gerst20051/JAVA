package a7_token;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class AToken {
	String token;
	
	public void setToken(String input) {
		token = input;
	}
	
	public String getToken() {
		return token;
	}
}