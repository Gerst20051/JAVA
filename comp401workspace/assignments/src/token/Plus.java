package token;

public class Plus {
	String plus;

	public Plus() {
		setPlus("+");
	}
	
	public Plus(String input) {
		setPlus(input);
	}
	
	public void setPlus(String input) {
		plus = input;
	}
	
	public String getPlus() {
		return plus;
	}
}