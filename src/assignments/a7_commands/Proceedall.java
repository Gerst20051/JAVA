package a7_commands;

import a6_token.Token;

public class Proceedall extends ACommand implements Token {
	public Proceedall(String word) {
		setToken(word);
	}
}