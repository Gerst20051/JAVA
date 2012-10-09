package recitation4;

public class CartesianRectangle implements Rectangle {
	int x, y;
	int width = 5, height = 1;

	Line top = new ALine(x,y,x+width,y);
	Line left = new ALine(x,y,x,y+height);
	Line right = new ALine(x+width,y,x+width,y+height);
	Line bottom = new ALine(x,y+height,x+width,y+height);

	public CartesianRectangle(int new_x, int new_y) {
		x = new_x;
		y = new_y;
		top.setX(new_x);
		left.setX(new_x);
		right.setX(new_x);
		bottom.setX(new_x);
		top.setY(new_y);
		left.setY(new_y);
		left.setY(new_y);
		right.setY(new_y);
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int val) {
		x = val;
		top.setX(val);
		left.setX(val);
		right.setX(val);
		bottom.setX(val);
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int val) {
		y = val;
		top.setY(val);
		left.setY(val);
		left.setY(val);
		right.setY(val);
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int val) {
		width = val;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int val) {
		height = val;
		left.setHeight(val);
		right.setHeight(val);
		bottom.setHeight(val);
	}
}