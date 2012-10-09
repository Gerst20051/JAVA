package recitation3;

import bus.uigen.ObjectEditor;

public class CartesianRectangle implements Rectangle {
	int x, y, width, height;

	public CartesianRectangle (int initX, int initY, int initWidth, int initHeight) {
		x = initX; 
		y = initY;
		width = initWidth;
		height = initHeight; 
	}

	public int getX() {return x;}
	public void setX(int newX) {x = newX;}
	public int getY() { return y; }
	public void setY(int newY) {y = newY;}
	public int getWidth() {return width;}
	public void setWidth(int newVal) {width = newVal;}
	public int getHeight() {return height;}
	public void setHeight(int newHeight) {height = newHeight;} 
	
	public static void main(String args[]) {
		ObjectEditor.edit(new CartesianRectangle(10, 0, 20, 200));
	}
}