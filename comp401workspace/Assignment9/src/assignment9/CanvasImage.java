package assignment9;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class CanvasImage implements GraphicObjectImage {
	ImageShape image;
	String[] filename;
	String dir = "src/images/";
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
	
	public String getImageFileName() {
		return dir+imagename;
	}
	
	public void show() {
		image.setImageFileName(getImageFileName());
	}
	
	public void hide() {
		image.setImageFileName(dir+"hidden.png");
	}
	
	public int getHeight() {
		return image.getHeight();
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public void setLocation(int x, int y) {
		image.setX(x);
		image.setY(y);
	}
	
	public int getX() {
		return image.getX();
	}
	
	public int getY() {
		return image.getY();
	}
}