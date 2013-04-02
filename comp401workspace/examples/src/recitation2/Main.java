package recitation2;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Main {
	public static void main(String[] args) {
		MathClass myobject = new MathClass();
		OEFrame editor = ObjectEditor.edit(myobject);
		editor.refresh();
	}
}