package objectpackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;



public class AStringShape implements StringShape{
	 	String text;
	    int x, y;

	    public AStringShape(String initText, int initX, int initY) {
	        text = initText;
	        x = initX;
	        y = initY;
	    }

		PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
		
		@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	    public void addPropertyChangeListener(PropertyChangeListener listener) {
			propertyListenerSupport.add(listener);
	    }

	    public int getX() {
	        return x;
	    }
	    public void setX(int newX) {
	        x = newX;
	    	propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "X",getX(),getX()));
	    }
	    public int getY() {
	        return y;
	    }
	    public void setY(int newY) {
	        y = newY;
	        propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Y",getY(),getY()));
	    }
	    public String getText() {
	        return text;
	    }
	    public void setText(String newVal) {
	        text = newVal;
	        propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Text",getText(),getText()));
			
	    }

	 

}
