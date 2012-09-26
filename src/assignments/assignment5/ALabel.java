package assignment5;

public class ALabel implements Label {
	int width, height;
	String text, imageFile;
	Point location;
	
	public ALabel (int initX, int initY, int initWidth, int initHeight, String initText, String theImageFile) {
		location = new ACartesianPoint(initX, initY);
		width = initWidth;
		height = initHeight;
		text = initText; 
		imageFile = theImageFile;
	}
	
	public Point getLocation() {return location;}
	public void setLocation(Point newVal) {location = newVal;}
	public int getWidth() {return width;}
	public void setWidth(int newVal) {width = newVal;}
	public int getHeight() {return height;}
	public void setHeight(int newHeight) {height = newHeight;}
	public String getText() {return text;}
	public void setText(String newVal) {text = newVal;}
	public String getImageFileName() {return imageFile;}
	public void setImageFileName(String newVal) {imageFile = newVal;}
}