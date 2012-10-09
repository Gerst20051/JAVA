package a7_commands;

import a6_token.Token;

public class Define extends ACommand implements Token {
	public Define(String word) {
		setToken(word);
	}
}