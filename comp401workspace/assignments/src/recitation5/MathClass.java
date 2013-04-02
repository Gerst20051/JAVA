package recitation5;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class MathClass implements Number, Sum, Factorial {
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	int n;
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	
	public void setNumber(int input) {
		int oldNumber = getNumber();
		int oldFactorial = getFactorial();
		int oldSum = getSum();
		n = input;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "Number", oldNumber, getNumber()));
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "Factorial", oldFactorial, getFactorial()));
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "Sum", oldSum, getSum()));
	}

	public int getNumber() {
		return n;
	}
	
	public int getFactorial() {
		if (2 > n) return 1;
		int factorial = 1, i;
		for (i = 2; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}
	
	public int getSum() {
		int sum = 0, i;
		for (i = n; i > 0; i--) {
			sum += i;
		}
		return sum;
	}
}