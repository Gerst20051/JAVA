package a4_commands;

import a4_token.Token;

public class Repeat implements Token {
	String token;
	
	public Repeat(String word) {
		setToken(word);
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}