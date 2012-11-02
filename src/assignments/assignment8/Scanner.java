package assignment8;

import a8_token.Token;

public interface Scanner {
	public void setString(String input);
	public String getString();
	public void setTokens(Token[] input);
	public Token[] getTokens();
}