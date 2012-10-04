package a5_commands;

import a5_token.Token;

public class Move extends ACommand implements Token {
	public Move(String word) {
		setToken(word);
	}
}