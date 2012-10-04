package assignment6;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class CanvasImage implements GraphicObject, GraphicObjectDisplayable, GraphicObjectImage {
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
	
	public void show() {
		image.setImageFileName(dir+imagename);
	}
	
	public void hide() {
		image.setImageFileName(dir+"hidden.png");
	}
	
	public void setLocation(int x, int y) {
		image.setX(x);
		image.setY(y);
	}
}