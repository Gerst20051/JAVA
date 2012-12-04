package assignment10;

import java.awt.Color;
import java.awt.Stroke;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
import util.annotations.Visible;
@StructurePattern("Line Pattern")

public class ALine implements Line {
	int x, y, width, height;
	Color color;
	Stroke stroke;
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	
	public ALine (int initX, int initY, int initWidth, int initHeight) {
		x = initX;
		y = initY;
		width = initWidth;
		height = initHeight;
	}
	
	public int getX() { return x; }
	public void setX(int newX) {
		x = newX;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "x", getX(), getX()));
	}
	public int getY() { return y; }
	public void setY(int newY) {
		y = newY;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "y", getY(), getY()));
	}
	public int getWidth() { return width; }
	public void setWidth(int newWidth) {
		width = newWidth;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "width", getWidth(), getWidth()));
	}
	public int getHeight() { return height; }
	public void setHeight(int newHeight) {
		height = newHeight;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "height", getHeight(), getHeight()));
	}
	
	@Visible(false)
	public Color getColor() { return color; }
	public void setColor(Color input) {
		color = input;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "color", getColor(), getColor()));
	}
	
	@Visible(false)
	public Stroke getStroke() { return stroke; }
	public void setStroke(Stroke input) {
		stroke = input;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "stroke", getStroke(), getStroke()));
	}
	
	public boolean equals(Object otherVal) {
		if (!(otherVal instanceof Line)) return false;
		Line otherLine = (Line) otherVal;
		return (x == otherLine.getX() && y == otherLine.getY() && width == otherLine.getWidth() && height == otherLine.getHeight());
	}
	
	public static void main(String args[]) {
		bus.uigen.ObjectEditor.edit(new ALine(10, 10, 20, 20));
	}
}