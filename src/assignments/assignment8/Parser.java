package assignment8;

import a8_token.Token;

public interface Parser {	
	public Token[] getTokens();
	public AObjectDatabase getHistory();
	public void setString(String input);
}