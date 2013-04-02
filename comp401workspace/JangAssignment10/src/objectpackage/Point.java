package objectpackage;

import util.models.PropertyListenerRegisterer;

public interface Point extends PropertyListenerRegisterer{
	public int getWidth(); 
    public int getHeight();  
    public double getAngle(); 
    public double getRadius(); 
}
