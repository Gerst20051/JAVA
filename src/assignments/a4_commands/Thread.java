package a4_commands;

import a4_token.Token;

public class Thread implements Token {
	String token;
	
	public Thread(String word) {
		setToken(word);
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}