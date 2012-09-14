package token;

public class End {
	String end;

	public End() {
		setEnd("}");
	}
	
	public End(String input) {
		setEnd(input);
	}
	
	public void setEnd(String input) {
		end = input;
	}
	
	public String getEnd() {
		return end;
	}
}