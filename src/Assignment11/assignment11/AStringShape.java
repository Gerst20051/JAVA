package assignment11;

import bus.uigen.ObjectEditor;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
@StructurePattern("String Pattern")

public class AStringShape implements StringShape {
	String text;
	int x, y;
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	
	public AStringShape(String initText, int initX, int initY) {
		text = initText;
		x = initX;
		y = initY;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int newX) {
		x = newX;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "x", getX(), getX()));
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int newY) {
		y = newY;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "y", getY(), getY()));
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String newVal) {
		text = newVal;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "text", getText(), getText()));
	}
	
	public static void main(String args[]) {
		StringShape string = new AStringShape("hello", 0, 10);
		ObjectEditor.edit(string);
		string.setX(100);
		string.setY(100);
	}
}