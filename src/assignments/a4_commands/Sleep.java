package a4_commands;

import a4_token.Token;

public class Sleep implements Token {
	String token;
	
	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}