package assignment8;

import a7_token.Token;
import bus.uigen.OEFrame;

public interface Parser {	
	public Token[] getTokens();
	public AObjectDatabase getHistory();
	public void setString(String input);
	public void reference(OEFrame object);
}