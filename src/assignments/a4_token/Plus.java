package a4_token;

public class Plus implements Token {
	String plus;

	public Plus() {
		setToken("+");
	}
	
	public Plus(String input) {
		setToken(input);
	}
	
	public void setToken(String input) {
		plus = input;
	}
	
	public String getToken() {
		return plus;
	}
}