package assignment6;

import java.awt.Color;
import java.awt.Stroke;
import util.annotations.StructurePattern;
import util.annotations.Visible;
@StructurePattern("Bean Pattern")

public class CanvasLine implements GraphicObject, GraphicObjectRotatable, GraphicObjectLine {
	double radius = 50, angle = 0;
	int width = 50, height = 0, reps = 32;
	Line line = new ALine(0, 0, width, height);
	Point point = new APolarPoint(radius, angle);
	
	public Line getLine() {
		return line;
	}

	@Visible(false)
	public Color getColor() {
		return line.getColor();
	}
	
	public void setColor(Color input) {
		line.setColor(input);
	}
	
	@Visible(false)
	public Stroke getStroke() {
		return line.getStroke();
	}
	
	public void setStroke(Stroke input) {
		line.setStroke(input);
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
	
	public double getAngle() {
		return angle;
	}

	public void rotate() {
		angle += Math.PI/reps;
		setAngle(radius, angle);
	}
	
	public void rotate(int reps) {
		while (0 < reps--) {
			rotate();
		}
	}
}