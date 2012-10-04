package a6_commands;

import a6_token.Token;

public class Call extends ACommand implements Token {
	public Call(String word) {
		setToken(word);
	}
}