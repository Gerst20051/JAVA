package a8_token;

public class Plus extends AToken implements Token {
	public Plus() {
		setToken("+");
	}
	
	public Plus(String input) {
		setToken(input);
	}
}