package a6_commands;

import a6_token.Token;

public class Say extends ACommand implements Token {
	public Say(String word) {
		setToken(word);
	}
}