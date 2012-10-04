package a5_commands;

import a5_token.Token;

public class Undo extends ACommand implements Token {
	public Undo(String word) {
		setToken(word);
	}
}