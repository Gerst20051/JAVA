package a7_commands;

import a6_token.Token;

public class RotateRightArm extends ACommand implements Token {
	public RotateRightArm(String word) {
		setToken(word);
	}
}