package a5_commands;

import a5_token.Token;

public class Repeat extends ACommand implements Token {
	public Repeat(String word) {
		setToken(word);
	}
}