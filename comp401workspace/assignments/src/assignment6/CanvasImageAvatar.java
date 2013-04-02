package assignment6;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class CanvasImageAvatar extends CanvasImage implements GraphicObjectImageAvatar {	
	public CanvasImageAvatar(String input) {
		super(input);
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