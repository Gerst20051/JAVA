package assignment5;

import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Image implements GraphicObject, GraphicObjectDisplayable, GraphicObjectImage {
	OEFrame OE;
	ImageShape image;
	String[] filename;
	String dir = "src/assignment5/";
	String imagename;
	String orientation;
	
	public Image(String input) {
		imagename = input;
		filename = imagename.split("\\.");
		image = new AShapeImage(dir+imagename, 0, 0);
		orientation = "forward";
	}
	
	public void display() {
		OE = ObjectEditor.edit(image);
	}
	
	public void setSize(int width, int height) {
		OE.setSize(width, height);
	}

	public void setLocation(int x, int y) {
		image.setX(x);
		image.setY(y);
	}
	
	public void lookLeft() {
		orientation = "left";
		image.setImageFileName(dir+filename[0]+"_Left."+filename[1]);
	}
	
	public void lookForward() {
		orientation = "forward";
		image.setImageFileName(dir+imagename);
	}
	
	public void lookRight() {
		orientation = "right";
		image.setImageFileName(dir+filename[0]+"_Right."+filename[1]);
	}
	
	public void lookBack() {
		orientation = "back";
		image.setImageFileName(dir+filename[0]+"_Back."+filename[1]);
	}
	
	public void turnHead(int reps) {
		while (0 < reps--) {
			setLocation(reps+100, reps+100);
			if (orientation == "forward") {
				lookRight();
			} else if (orientation == "right") {
				lookBack();
			} else if (orientation == "back") {
				lookLeft();
			} else if (orientation == "left") {
				lookForward();
			}
			OE.refresh();
			ThreadSupport.sleep(300);
		}
	}
}