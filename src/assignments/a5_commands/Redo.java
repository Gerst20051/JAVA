package a5_commands;

import a5_token.Token;

public class Redo extends ACommand implements Token {
	public Redo(String word) {
		setToken(word);
	}
}