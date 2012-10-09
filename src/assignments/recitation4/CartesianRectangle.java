package recitation4;

public class CartesianRectangle implements Rectangle {
	int x, y;
	int width = 10, height = 1;

	Line top = new ALine(x,y,x+width,y);
	Line left = new ALine(x,y,x,y+height);
	Line right = new ALine(x+width,y,x+width,y+height);
	Line bottom = new ALine(x,y+height,x+width,y+height);

	public CartesianRectangle(int x, int y) {
		this.x = x;
		this.y = y;
		top.setX(x);
		left.setX(x);
		right.setX(x);
		bottom.setX(x);
		top.setY(y);
		left.setY(y);
		left.setY(y);
		right.setY(y);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int input) {
		x = input;
		top.setX(input);
		left.setX(input);
		right.setX(input);
		bottom.setX(input);
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int input) {
		y = input;
		top.setY(input);
		left.setY(input);
		left.setY(input);
		right.setY(input);
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int input) {
		width = input;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int input) {
		height = input;
		left.setHeight(input);
		right.setHeight(input);
		bottom.setHeight(input);
	}
}