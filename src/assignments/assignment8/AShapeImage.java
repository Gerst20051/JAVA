package assignment8;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import bus.uigen.ObjectEditor;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
@StructurePattern("Image Pattern")

public class AShapeImage implements ImageShape {
	int x, y, imageHeight, imageWidth;
	String imageFileName;
	Icon icon;
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	
	public AShapeImage(String initImageFileName, int initX, int initY) {
		imageFileName = initImageFileName;
		x = initX;
		y = initY;
		icon = new ImageIcon(imageFileName);
		imageHeight = icon.getIconHeight();
		imageWidth = icon.getIconWidth();
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
	public String getImageFileName() { return imageFileName; }
	public void setImageFileName(String newVal) {
		imageFileName = newVal;
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "imageFileName", getImageFileName(), getImageFileName()));
	}
	public int getHeight() { return imageHeight; }
	public int getWidth() { return imageWidth; }

	public static void main (String args[]) {
		ImageShape shuttle = new AShapeImage("src/images/Dorothy.jpg", 0, 0);
		ObjectEditor.edit(shuttle);
		shuttle.setX(100);
		shuttle.setY(100);
	}
}