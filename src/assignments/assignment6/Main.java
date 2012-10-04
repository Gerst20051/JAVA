package assignment6;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Main {
	public static void main (String[] args) {
		Canvas canvas = new Canvas();
		OEFrame CanvasOE = ObjectEditor.edit(canvas);
		CanvasOE.hideMainPanel();
		CanvasOE.setSize(650, 695);
		canvas.reference(CanvasOE);
		canvas.init();
	}
}