package a6_commands;

import a6_token.Token;

public class Undo extends ACommand implements Token {
	public Undo(String word) {
		setToken(word);
	}
}