package assignment9;

import java.awt.Color;
import java.awt.Stroke;
import java.beans.PropertyChangeListener;

public interface Line {
    public int getX();
    public void setX(int newVal);
    public int getY();
    public void setY(int newVal);
    public int getWidth();
    public void setWidth(int newVal);
    public int getHeight();
    public void setHeight(int newVal);
	public Color getColor();
	public void setColor(Color newVal);
	public Stroke getStroke();
	public void setStroke(Stroke newVal);
	public void addPropertyChangeListener(PropertyChangeListener listener);
}