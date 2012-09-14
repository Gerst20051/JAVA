package token;

public class Start {
	String start;

	public Start() {
		setStart("{");
	}
	
	public Start(String input) {
		setStart(input);
	}
	
	public void setStart(String input) {
		start = input;
	}
	
	public String getStart() {
		return start;
	}
}