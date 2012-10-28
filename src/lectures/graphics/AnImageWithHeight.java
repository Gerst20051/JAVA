package graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import bus.uigen.ObjectEditor;

public class AnImageWithHeight implements ImageWithHeight {
	int x, y;
	String imageFileName;
	int imageHeight, imageWidth;
	
	public AnImageWithHeight(String anImageFileName) {
		imageFileName = anImageFileName;
		Icon icon = new ImageIcon(imageFileName);
		imageHeight = icon.getIconHeight();
		imageWidth = icon.getIconWidth();
	}
	
	public int getX() { return x; }
	public void setX(int newX) { x = newX; }
	public int getY() { return y; }
	public void setY(int newY) { y = newY; }
	public String getImageFileName() { return imageFileName; }
	public int getHeight() { return  imageHeight; }
	public int getWidth() { return  imageWidth; }
	
	public static void main (String args[]) {
		final String IMAGE_FILE_NAME = "src/graphics/image.jpg";
		ImageWithHeight image = new AnImageWithHeight(IMAGE_FILE_NAME);
		ObjectEditor.edit(image);
		image.setX(100);
		image.setY(100);
	}
}