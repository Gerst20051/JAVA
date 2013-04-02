package assignment11;

import java.beans.PropertyChangeListener;

public interface StringShape {
	public int getX();
	public void setX(int newX);
	public int getY();
	public void setY(int newY);
	public String getText();
	public void setText(String newVal);
	public void addPropertyChangeListener(PropertyChangeListener listener);
}