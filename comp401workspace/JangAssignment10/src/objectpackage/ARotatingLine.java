package objectpackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import bus.uigen.ObjectEditor;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;

public class ARotatingLine implements RotatingLine{
	Point mypoint;
	int x,y;
	public ARotatingLine (int initX, int initY, double initradius, double initangle) {
	        x = initX; 
	        y = initY;
	        mypoint = new APolarPoint(initradius, initangle);    
	}
	
	PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
    public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
    }

	public void setX(int newX) {
		x = newX;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "X",getX(),getX()));
	}
	
	public void setY(int newY) {
		y = newY;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Y",getY(),getY()));
		}
	
	public int getX(){
		return x;
	}
    public int getY(){
    	return y;
    }
    public int getWidth() {
    	return mypoint.getWidth();
    }    
    public int getHeight() {
    	return mypoint.getHeight(); 
    }
    public void setAngle(double newAngle) { 
    	mypoint = new APolarPoint(mypoint.getRadius(),newAngle);
    }
    public void setRadius(double newRadius) { 
    	mypoint = new APolarPoint(newRadius, mypoint.getAngle());
    }
    public void changeangle(int newUnit){
    	setAngle(mypoint.getAngle() + newUnit*((Math.PI)/32));
    }
    
}
