package graphics;

import bus.uigen.ObjectEditor;
import util.annotations.StructurePattern;
@StructurePattern("Image Pattern")

public class AShapeImage implements ImageShape {
	String imageFileName;
	int x, y;
	
	public AShapeImage(String initImageFileName, int initX, int initY) {
		imageFileName = initImageFileName;
		x = initX;
		y = initY;
	}
	
	public int getX() { return x; }
	public void setX(int newX) { x = newX; }
	public int getY() { return y; }
	public void setY(int newY) { y = newY; }
	public String getImageFileName() { return imageFileName; }
	public void setImageFileName(String newVal) { imageFileName = newVal; }

	public static void main (String args[]) {
		ImageShape shuttle = new AShapeImage("src/assignment5/Dorothy.jpg", 0, 0);
		ObjectEditor.edit(shuttle);
		shuttle.setX(100);
		shuttle.setY(100);
	}
}