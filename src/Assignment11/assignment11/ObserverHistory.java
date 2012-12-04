package assignment11;

public interface ObserverHistory {
	public void addElement(CounterObserver element);
	public CounterObserver elementAt(int index);
	public int size();
}