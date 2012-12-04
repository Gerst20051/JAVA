package assignment9;

import a9_token.Token;

public interface Parser {	
	public Token[] getTokens();
	public AObjectDatabase getHistory();
	public void setString(String input);
}