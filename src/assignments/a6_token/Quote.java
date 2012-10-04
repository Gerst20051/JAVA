package a6_token;

public class Quote extends AToken implements Token {
	public Quote() {
		setToken("\"Blah1\"");
	}
	
	public Quote(String input) {
		setToken(input);
	}
}