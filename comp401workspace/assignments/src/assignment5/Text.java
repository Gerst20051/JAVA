package assignment5;

import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Text implements GraphicObject, GraphicObjectDisplayable, GraphicObjectText {
	OEFrame OE;
	StringShape string;
	
	public Text(String input) {
		string = new AStringShape(input, 0, 0);
	}
	
	public void display() {
		OE = ObjectEditor.edit(string);
	}
	
	public void setLocation(int x, int y) {
		string.setX(x);
		string.setY(y);
	}
	
	public void rotateText(int reps) {
		String[] text = {"Wizard of Oz","Random String","Yay!","Interesting!","Last Text String in Array"};
		while (0 < reps--) {
			setLocation(reps+100, reps+100);
			string.setText(text[(int) Math.floor(Math.random()*text.length)]);
			OE.refresh();
			ThreadSupport.sleep(300);
		}
	}
}