package a4_token;

public class Minus implements Token {
	String minus;

	public Minus() {
		setToken("-");
	}
	
	public Minus(String input) {
		setToken(input);
	}
	
	public void setToken(String input) {
		minus = input;
	}
	
	public String getToken() {
		return minus;
	}
}