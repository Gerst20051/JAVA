package a4_token;

public class End implements Token {
	String end;

	public End() {
		setToken("}");
	}
	
	public End(String input) {
		setToken(input);
	}
	
	public void setToken(String input) {
		end = input;
	}
	
	public String getToken() {
		return end;
	}
}