package compositepackage;

import objectpackage.RotatingLine;

public interface Angle {
	public void setX(int newX);
	public int getX();
	
	public void setY(int newY);
	public int getY();
	
	public RotatingLine getLine1();
	public RotatingLine getLine2();
}