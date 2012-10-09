package a7_token;

public class Minus extends AToken implements Token {
	public Minus() {
		setToken("-");
	}
	
	public Minus(String input) {
		setToken(input);
	}
}