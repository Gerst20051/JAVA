package a5_commands;

import a5_token.Token;

public class Call extends ACommand implements Token {
	public Call(String word) {
		setToken(word);
	}
}