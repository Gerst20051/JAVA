package a7_commands;

import a6_token.Token;

public class Move extends ACommand implements Token {
	public Move(String word) {
		setToken(word);
	}
}