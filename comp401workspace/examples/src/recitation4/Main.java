package recitation4;

import bus.uigen.ObjectEditor;

public class Main {
	public static void main(String[] args) {
		MathClassStack myStack = new MyMathClassStack();
		
		for (int i = 0; i < 10; i++) {
			myStack.push(new MathClass(i));
		}
		
		ObjectEditor.edit(myStack);
	}
}