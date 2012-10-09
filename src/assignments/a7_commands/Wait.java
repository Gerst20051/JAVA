package a7_commands;

import a6_token.Token;

public class Wait extends ACommand implements Token {
	public Wait(String word) {
		setToken(word);
	}
}