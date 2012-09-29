package assignment5;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class CanvasAngle {
	boolean isOrigin = false;
	double radius = 100, firstangle = 0, angle = Math.PI/4, origin = angle/2;
	int width = 100, height = 0, reps = 32;
	Line line = new ALine(0, 0, width, height);
	Line line2 = new ALine(0, 0, width, height);
	Point point = new APolarPoint(radius, angle);
	
	public CanvasAngle() {
		setAngle();
	}
	
	public void setLocation(int x, int y) {
		line.setX(x);
		line.setY(y);
		line2.setX(x);
		line2.setY(y);
	}
	
	public int getReps() {
		return reps;
	}
	
	@SuppressWarnings("unused")
	private boolean isOrigin() {
		return isOrigin;
	}
	
	public void isOrigin(boolean value) {
		isOrigin = value;
	}
	
	public void setOrigin(double value) {
		origin = value;
	}
	
	public double getOrigin() {
		return origin;
	}
	
	public void setOriginAngle() {
		point = new APolarPoint(radius, origin+(angle/2));
		int width = point.getX(), height = point.getY();
		line2.setWidth(width);
		line2.setHeight(height);
		point = new APolarPoint(radius, origin-(angle/2));
		width = point.getX();
		height = point.getY();
		line.setWidth(width);
		line.setHeight(height);
	}
	
	public void setAngle() {
		point = new APolarPoint(radius, angle+firstangle);
		int width = point.getX(), height = point.getY();
		line2.setWidth(width);
		line2.setHeight(height);
	}
	
	public void setAngle(double input) {
		angle = input;
		setAngle();
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setFirstAngle(double firstangle) {
		point = new APolarPoint(radius, firstangle);
		int width = point.getX(), height = point.getY();		
		line.setWidth(width);
		line.setHeight(height);
		setAngle();
	}
	
	public Line getFirstLine() {
		return line;
	}
	
	public Line getSecondLine() {
		return line2;
	}
	
	public void rotate() {
		firstangle += Math.PI/reps;
		setFirstAngle(firstangle);
	}
	
	public void rotateSecond() {
		angle += Math.PI/reps;
		setAngle(angle);
	}
}