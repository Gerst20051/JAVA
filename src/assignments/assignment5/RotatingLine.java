package assignment5;

import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class RotatingLine {
	OEFrame OE;
	double radius = 100, angle = 0;
	int width = 100, height = 0, reps = 32;
	Line line = new ALine(0, 0, width, height);
	Point point = new APolarPoint(radius, angle);
	
	public RotatingLine() {
		OE = ObjectEditor.edit(line);
	}
	
	public void setLocation(int x, int y) {
		line.setX(x);
		line.setY(y);
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
	
	public void rotate(int reps) {
		while (0 < reps--) {
			setLocation(reps+100, reps+100);
			rotate();
			OE.refresh();
			ThreadSupport.sleep(100);
		}
	}
	
	public void doCircle() {
		setLocation(100, 100);
		rotate(2*reps);
	}
}