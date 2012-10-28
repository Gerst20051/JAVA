package assignment8;

import a7_token.Token;
import bus.uigen.OEFrame;

public interface Scanner {
	public void setString(String input);
	public String getString();
	public void setTokens(Token[] input);
	public Token[] getTokens();
	public void reference(OEFrame editor);
}