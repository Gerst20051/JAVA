package a5_commands;

import a5_token.Token;

public class Wait extends ACommand implements Token {
	public Wait(String word) {
		setToken(word);
	}
}