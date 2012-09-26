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

	public void rotate() {
		angle += Math.PI / reps;
		point = new APolarPoint(radius, angle);
		int width = point.getX(), height = point.getY();		
		line.setWidth(width);
		line.setHeight(height);
	}
	
	public void rotate(int reps) {
		while (0 < reps--) {
			ThreadSupport.sleep(100);
			rotate();
			OE.refresh();
		}
	}
	
	public void doCircle() {
		rotate(2*reps);
	}
}