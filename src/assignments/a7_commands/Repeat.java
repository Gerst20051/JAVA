package a7_commands;

import a6_token.Token;

public class Repeat extends ACommand implements Token {
	public Repeat(String word) {
		setToken(word);
	}
}