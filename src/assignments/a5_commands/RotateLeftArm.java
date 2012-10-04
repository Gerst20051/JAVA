package a5_commands;

import a5_token.Token;

public class RotateLeftArm extends ACommand implements Token {
	public RotateLeftArm(String word) {
		setToken(word);
	}
}