package assignment10;

import a10_token.Token;

public interface Parser {	
	public Token[] getTokens();
	public AObjectDatabase getHistory();
	public void setString(String input);
}