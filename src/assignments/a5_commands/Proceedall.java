package a5_commands;

import a5_token.Token;

public class Proceedall extends ACommand implements Token {
	public Proceedall(String word) {
		setToken(word);
	}
}