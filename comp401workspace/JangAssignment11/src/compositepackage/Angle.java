package compositepackage;

import java.beans.PropertyChangeListener;

import objectpackage.RotatingLine;
import util.models.PropertyListenerRegisterer;

public interface Angle extends PropertyListenerRegisterer{
	public void setX(int newX);
	public int getX();
	
	public void setY(int newY);
	public int getY();
	
	public RotatingLine getLine1();
	public RotatingLine getLine2();
	public void addPropertyChangeListener(PropertyChangeListener listener);
}