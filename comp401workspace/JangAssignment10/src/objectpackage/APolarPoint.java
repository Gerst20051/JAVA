package objectpackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import bus.uigen.ObjectEditor;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;

public class APolarPoint implements Point{
	double radius, angle;

	PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
	}

    public APolarPoint(double theRadius, double theAngle) {
        radius = theRadius;
        angle = theAngle;
	}
    public APolarPoint(int theX, int theY) {
        radius = Math.sqrt(theX*theX + theY*theY);
        angle = Math.atan((double) theY/theX);
    }
    public void setWidth() {
    	propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Width",getWidth(),getWidth()));
    }
    
    public void setHeight() {
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Height",getHeight(),getHeight()));
    }
    
    public void setAngle() {
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Angle",getAngle(),getAngle()));
    }
    
    public void setRadius() {
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Radius",getRadius(),getRadius()));
    }
    
    public int getWidth() { return (int) (radius*Math.cos(angle)); }
    public int getHeight() { return (int) (radius*Math.sin(angle)); }
    public double getAngle() { return angle; } 
    public double getRadius() { return radius;} 
}
