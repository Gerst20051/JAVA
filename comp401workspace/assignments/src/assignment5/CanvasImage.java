package assignment5;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class CanvasImage {
	ImageShape image;
	String[] filename;
	String dir = "src/assignment5/";
	String imagename;
	String orientation;
	
	public CanvasImage(String input) {
		imagename = input;
		filename = imagename.split("\\.");
		image = new AShapeImage(dir+imagename, 0, 0);
		orientation = "forward";
	}

	public ImageShape getImage() {
		return image;
	}
	
	public void hideImage() {
		image.setImageFileName(dir+"hidden.png");
	}
	
	public void showImage() {
		image.setImageFileName(dir+imagename);
	}
	
	public void setLocation(int x, int y) {
		image.setX(x);
		image.setY(y);
	}
	
	public void setOrientation(String input) {
		orientation = input;
	}
	
	public String getOrientation() {
		return orientation;
	}
	
	public void lookLeft() {
		setOrientation("left");
		image.setImageFileName(dir+filename[0]+"_Left."+filename[1]);
	}
	
	public void lookForward() {
		setOrientation("forward");
		image.setImageFileName(dir+imagename);
	}
	
	public void lookRight() {
		setOrientation("right");
		image.setImageFileName(dir+filename[0]+"_Right."+filename[1]);
	}
	
	public void lookBack() {
		setOrientation("back");
		image.setImageFileName(dir+filename[0]+"_Back."+filename[1]);
	}
}