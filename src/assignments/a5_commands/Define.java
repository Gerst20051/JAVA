package a5_commands;

import a5_token.Token;

public class Define extends ACommand implements Token {
	public Define(String word) {
		setToken(word);
	}
}