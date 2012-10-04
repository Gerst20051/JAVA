package a5_commands;

import a5_token.Token;

public class Say extends ACommand implements Token {
	public Say(String word) {
		setToken(word);
	}
}