package a9_token;

public class End extends AToken implements Token {
	public End() {
		setToken("}");
	}
	
	public End(String input) {
		setToken(input);
	}
}