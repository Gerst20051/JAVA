package objectpackage;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;

public interface RotatingLine extends PropertyListenerRegisterer {

	    public int getX(); 
	    public void setX(int newVal);
	    public int getY();
	    public void setY(int newVal);
	    public int getHeight();
	    public int getWidth();
	    public void changeangle(int newVal);
	    public void addPropertyChangeListener(PropertyChangeListener listener);
}
