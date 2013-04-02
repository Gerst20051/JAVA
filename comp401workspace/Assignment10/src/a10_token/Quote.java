package a10_token;

public class Quote extends AToken implements Token {
	public Quote() {
		setToken("Blah1");
	}
	
	public Quote(String input) {
		setToken(input.substring(1,input.length()-1));
	}
}