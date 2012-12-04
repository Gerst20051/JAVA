package a4_commands;

import a4_token.Token;

public class Wait implements Token {
	String token;
	
	public Wait(String word) {
		setToken(word);
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}