package recitation4;

import java.util.List;
import java.util.LinkedList;

public class MyMathClassStack implements MathClassStack {
	List elements = new LinkedList();
	
	public boolean isEmpty() {
		return elements.size() == 0;
	}
	
	public MathClass getTop() {
		return (MathClass) elements.get(0);
	}
	
	public void push(MathClass element) {
		elements.add(0, element);
	}
	
	public void pop() {
		elements.remove(0);
	}
}