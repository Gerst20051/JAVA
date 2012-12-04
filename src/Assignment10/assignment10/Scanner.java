package assignment10;

import a10_token.Token;

public interface Scanner {
	public void setString(String input);
	public String getString();
	public void setTokens(Token[] input);
	public Token[] getTokens();
}