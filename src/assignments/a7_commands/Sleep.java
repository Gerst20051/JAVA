package a7_commands;

import a6_token.Token;

public class Sleep extends ACommand implements Token {
	public Sleep(String word) {
		setToken(word);
	}
}