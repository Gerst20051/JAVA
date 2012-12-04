package a4_commands;

import a4_token.Token;

public class Call implements Token {
	String token;
	
	public Call(String word) {
		setToken(word);
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}