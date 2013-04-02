package assignment9;

import java.beans.PropertyChangeListener;

public interface ImageShape {
	public int getX();
	public void setX(int newX);
	public int getY();
	public void setY(int newY);
	public String getImageFileName();
	public void setImageFileName(String newVal);
	public int getHeight();
	public int getWidth();
	public void addPropertyChangeListener(PropertyChangeListener listener);
}