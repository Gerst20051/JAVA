package assignment5;

import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class CanvasRotatingLine {
	double radius = 100, angle = 0;
	int width = 100, height = 0, reps = 32;
	Line line = new ALine(0, 0, width, height);
	Point point = new APolarPoint(radius, angle);
	
	public Line getLine() {
		return line;
	}
	
	public void setLocation(int x, int y) {
		line.setX(x);
		line.setY(y);
	}
	
	public int getReps() {
		return reps;
	}
	
	public void setAngle(double radius, double angle) {
		point = new APolarPoint(radius, angle);
		int width = point.getX(), height = point.getY();		
		line.setWidth(width);
		line.setHeight(height);
	}

	public void rotate() {
		angle += Math.PI/reps;
		setAngle(radius, angle);
	}
}