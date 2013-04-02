package a4_token;

public class Start implements Token {
	String start;

	public Start() {
		setToken("{");
	}
	
	public Start(String input) {
		setToken(input);
	}
	
	public void setToken(String input) {
		start = input;
	}
	
	public String getToken() {
		return start;
	}
}