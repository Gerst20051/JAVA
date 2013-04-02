package assignment5;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class CanvasText {
	StringShape string = new AStringShape("", 0, 0);
	
	public CanvasText(){}
	public CanvasText(String input) {
		string.setText(input);
	}
	
	public void setLocation(int x, int y) {
		string.setX(x);
		string.setY(y);
	}
	
	public StringShape getText() {
		return string;
	}
	
	public void setText(String input) {
		string.setText(input);
	}
}