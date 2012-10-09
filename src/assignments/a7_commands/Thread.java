package a7_commands;

import a6_token.Token;

public class Thread extends ACommand implements Token {
	public Thread(String word) {
		setToken(word);
	}
}