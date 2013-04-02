package assignment8;

import java.awt.Color;
import java.awt.Stroke;
import util.annotations.StructurePattern;
import util.annotations.Visible;
@StructurePattern("Bean Pattern")

public class CanvasAngle implements GraphicObjectAngle {
	boolean isOrigin = false;
	double radius = 50, firstangle = 0, angle = Math.PI/2, origin = Math.PI/2, scale = 1;
	int width = 50, height = 0, reps = 32;
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
	
	public void setScale(double input) {
		scale = input;
		setOriginAngle();
	}
	
	public double getScale() {
		return scale;
	}
	
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
		point = new APolarPoint(radius*scale, origin+(angle/2));
		int width = point.getX(), height = point.getY();
		line2.setWidth(width);
		line2.setHeight(height);
		point = new APolarPoint(radius*scale, origin-(angle/2));
		width = point.getX();
		height = point.getY();
		line.setWidth(width);
		line.setHeight(height);
	}
	
	public void setAngle() {
		point = new APolarPoint(radius*scale, getAngle());
		int width = point.getX(), height = point.getY();
		line2.setWidth(width);
		line2.setHeight(height);
	}
	
	public void setAngle(double input) {
		angle = input;
		setAngle();
	}
	
	public double getAngle() {
		if (isOrigin()) {
			return firstangle+angle;
		} else {
			return angle;
		}
	}
	
	public void setFirstAngle() {
		point = new APolarPoint(radius*scale, getFirstAngle());
		int width = point.getX(), height = point.getY();		
		line.setWidth(width);
		line.setHeight(height);
		if (isOrigin()) {
			setAngle();
		}
	}
	
	public void setFirstAngle(double input) {
		firstangle = input;
		setFirstAngle();
	}
	
	public double getFirstAngle() {
		if (isOrigin()) {
			return Math.PI-(angle/2);
		} else {
			return firstangle;
		}
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
	
	public void rotate(int reps) {
		while (0 < reps--) {
			rotate();
		}
	}
	
	public void rotateSecond() {
		angle += Math.PI/reps;
		setAngle(angle);
	}
	
	public void rotateSecond(int reps) {
		while (0 < reps--) {
			rotateSecond();
		}
	}
	
	@Visible(false)
	public Color getColor() {
		return line.getColor();
	}
	
	public void setColor(Color input) {
		line.setColor(input);
		line2.setColor(input);
	}
	
	@Visible(false)
	public Stroke getStroke() {
		return line.getStroke();
	}
	
	public void setStroke(Stroke input) {
		line.setStroke(input);
		line2.setStroke(input);
	}
}