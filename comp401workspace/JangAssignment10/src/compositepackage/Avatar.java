package compositepackage;

import java.beans.PropertyChangeListener;

import objectpackage.ImageInterface;
import objectpackage.RotatingLine;
import objectpackage.StringShape;

public interface Avatar{
	public StringShape getText();
	public void setText(String string);
	public void setX(int newX);
	public void setY(int newY);
	public int getX();
	public int getY(); 
	public ImageInterface getHead();
	public Angle getArms();
	public Angle getLegs();
	public RotatingLine getTorso();
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
}
