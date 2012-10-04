package a6_commands;

import a6_token.Token;

public class Redo extends ACommand implements Token {
	public Redo(String word) {
		setToken(word);
	}
}