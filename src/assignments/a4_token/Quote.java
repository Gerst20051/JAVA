package a4_token;

public class Quote implements Token {
	String quote;

	public Quote() {
		setToken("\"Blah1\"");
	}
	
	public Quote(String input) {
		setToken(input);
	}
	
	public void setToken(String input) {
		quote = input;
	}
	
	public String getToken() {
		return quote;
	}
}