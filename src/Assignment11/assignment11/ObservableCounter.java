package assignment11;

public interface ObservableCounter extends Counter {    
	public void addObserver(CounterObserver observer);
	//public void removeObserver(CounterObserver observer);
}