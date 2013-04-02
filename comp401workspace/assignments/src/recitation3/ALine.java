package recitation3;

public class ALine implements Line {
	int x, y, width = 0, height = 0;
	
	public ALine (int newX, int newY, int newWidth, int newHeight) {
		x = newX;
		y = newY;
		width = newWidth;
		height = newHeight;
	}

	public int getX() {return x;}
	public void setX(int input) {x = input;}
	public int getY() {return y;}
	public void setY(int input) {y = input;}
	public int getWidth() {return width;}

	public void setWidth(int input) {
		int temp = width;
		width = input;
		height = (int) ((double) width / (double) temp * (double) height);
	}
	public int getHeight() {return height;}
	public void setHeight(int input) {
		int temp = height;
		height = input;
		width = (int) ((double) height / (double) temp * (double) width);
	}
}