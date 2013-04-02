package assignment5;

import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class Angle implements GraphicObject, GraphicObjectAngle, GraphicObjectReference {
	OEFrame OE;
	boolean isOrigin = false;
	double radius = 100, firstangle = 0, angle = Math.PI/4, origin = angle/2;
	int width = 100, height = 0, reps = 32;
	Line line = new ALine(0, 0, width, height);
	Line line2 = new ALine(0, 0, width, height);
	Point point = new APolarPoint(radius, angle);
	
	public Angle() {
		setAngle();
	}
	
	public void setLocation(int x, int y) {
		line.setX(x);
		line.setY(y);
		line2.setX(x);
		line2.setY(y);
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
	
	public void animateOrigin() {
		int direction = 1, total = reps*9;
		double oldangle = angle;
		angle = 0;
		setLocation(150, 100);
		for (int i = total; 0 < i; i--) {
			if (i < total) {
				if (i%reps == 0) direction = -direction;
				if (i%reps*2 == 0) origin += Math.PI/4;
			}
			angle += direction*(Math.PI/reps);
			setOriginAngle();
			ThreadSupport.sleep(25);
			OE.refresh();
		}
		angle = oldangle;
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
	
	public void rotate(int reps) {
		while (0 < reps--) {
			setLocation(reps+100, reps+100);
			rotate();
			ThreadSupport.sleep(100);
			OE.refresh();
		}
	}
	
	public void doCircle() {
		setLocation(100, 100);
		rotate(2*reps);
	}
	
	public void rotateSecond() {
		angle += Math.PI/reps;
		setAngle(angle);
	}
	
	public void rotateSecond(int reps) {
		while (0 < reps--) {
			setLocation(reps+100, reps+100);
			rotateSecond();
			ThreadSupport.sleep(100);
			OE.refresh();
		}
	}
	
	public void doCircleSecond() {
		setLocation(100, 100);
		rotateSecond(2*reps);
	}

	public void reference(OEFrame object) {
		OE = object;
	}
}