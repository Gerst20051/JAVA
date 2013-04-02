package recitation4;

public interface MathClassStack {
	public boolean isEmpty();
	public MathClass getTop();
	public void push(MathClass element);
	public void pop();
}