package a8_token;

public class Start extends AToken implements Token {
	public Start() {
		setToken("{");
	}
	
	public Start(String input) {
		setToken(input);
	}
}