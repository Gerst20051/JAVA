package recitation5;

import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;

public class MathClass_Skeleton implements Number, Sum, Factorial {
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		
	}
	
	public void setNumber(int newNumber) {
		
	}
	
	public int getNumber() {
		
	}
	
	public int getSum() {
		
	}
	
	public int getFactorial() {
		
	}
	
	public void notifyAllListeners(PropertyChangeEvent event) {
		
	}
}