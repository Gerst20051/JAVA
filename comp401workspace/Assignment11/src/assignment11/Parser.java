package assignment11;

import a11_token.Token;

public interface Parser {	
	public Token[] getTokens();
	public AObjectDatabase getHistory();
	public void setString(String input);
}