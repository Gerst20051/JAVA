package a5_commands;

import a5_token.Token;

public class Thread extends ACommand implements Token {
	public Thread(String word) {
		setToken(word);
	}
}