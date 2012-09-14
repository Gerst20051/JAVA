package token;

public class Quote {
	String quote;

	public Quote() {
		setQuote("\"Blah1\"");
	}
	
	public Quote(String input) {
		setQuote(input);
	}
	
	public void setQuote(String input) {
		quote = input;
	}
	
	public String getQuote() {
		return quote;
	}
}