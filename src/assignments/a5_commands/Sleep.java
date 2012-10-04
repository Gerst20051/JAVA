package a5_commands;

import a5_token.Token;

public class Sleep extends ACommand implements Token {
	public Sleep(String word) {
		setToken(word);
	}
}