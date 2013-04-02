package token;

public class Minus {
	String minus;

	public Minus() {
		setMinus("-");
	}
	
	public Minus(String input) {
		setMinus(input);
	}
	
	public void setMinus(String input) {
		minus = input;
	}
	
	public String getMinus() {
		return minus;
	}
}