package a7_commands;

import a6_token.Token;

public class RotateLeftArm extends ACommand implements Token {
	public RotateLeftArm(String word) {
		setToken(word);
	}
}