package recitation4;

public class ALine implements Line {
	int x, y, width = 0, height = 0;
	public ALine (int newX, int newY, int newWidth, int newHeight) {
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
	}

	public int getX() {return x;}
	public void setX(int newX) {x = newX;}
	public int getY() {return y;}
	public void setY(int newY) {y = newY;}
	public int getWidth() {return width;}

	public void setWidth(int newWidth) {
		int temp = width;
		width = newWidth;
		height = (int)((double)width/(double)temp * (double)height);
	}
	public int getHeight() {return height;}
	public void setHeight(int newHeight) {
		int temp = height;
		height = newHeight;
		width = (int)((double)height/(double)temp * (double)width);
	}
}