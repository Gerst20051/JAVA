package compositepackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import objectpackage.RotatingLine;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.Visible;

public class AnAngle implements Angle {
	int x,y; 
	RotatingLine line1, line2;
	public AnAngle(int initx, int inity, RotatingLine initLine1, RotatingLine initLine2){
		line1 = initLine1;
		line2 = initLine2;
	    setX(initx);
		setY(inity);
	}

	public RotatingLine getLine1() {
		return line1;
	}
	public RotatingLine getLine2() {
		return line2; 
	}
	@Visible(false)
	public int getX() {
		return x;
	}
	@Visible(false)
	public int getY() {
		return y;
	}
	public void setX(int newX) {
		x = newX;
		line1.setX(x);
		line2.setX(x);
	}
	public void setY(int newY) {
		y = newY; 
		line1.setY(y);
		line2.setY(y);
	}

	
}
