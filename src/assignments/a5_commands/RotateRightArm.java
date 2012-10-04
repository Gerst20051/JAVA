package a5_commands;

import a5_token.Token;

public class RotateRightArm extends ACommand implements Token {
	public RotateRightArm(String word) {
		setToken(word);
	}
}