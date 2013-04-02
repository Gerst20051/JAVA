package objectpackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;

public class AnImage implements ImageInterface{
	String imageFileName;
	int x,y;
	public AnImage (String initImageFileName, int iniX, int iniY){
		imageFileName = initImageFileName; 
		x = iniX;
		y = iniY;
	}
	PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
	}

    public int getX() {return x;}
    public void setX(int newX) {
    	x = newX;
    	propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "X",getX(),getX()));
    	
    }
    public int getY() { return y; }
    public void setY(int newY) {
    	y = newY;
    	propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Y",getY(),getY()));
    	
    }
    
    public String getImageFileName() {return imageFileName;}  
 
}