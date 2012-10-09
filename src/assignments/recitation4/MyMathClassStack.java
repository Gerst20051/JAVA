package recitation4;

import java.util.List;
import java.util.LinkedList;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern") 

public class MyMathClassStack implements MathClassStack {
	List<MathClass> elements = new LinkedList<MathClass>();
	
	public boolean isEmpty() {
		return elements.size() == 0;
	}
	
	public MathClass getTop() {
		return elements.get(0);
	}
	
	public void push(MathClass element) {
		elements.add(0, element);
	}
	
	public MathClass pop() {
		return elements.remove(0);
	}
}