package assignment10;

import java.awt.Color;
import java.awt.Stroke;
import util.annotations.StructurePattern;
import util.annotations.Visible;
@StructurePattern("Bean Pattern")

public class CanvasLine implements GraphicObjectLine {
	double radius = 50, angle = 0, scale = 1;
	int width = 50, height = 0, reps = 32;
	Line line = new ALine(0, 0, (int) (width*scale), (int) (height*scale));
	Point point = new APolarPoint(radius*scale, angle);
	
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
	
	public double getRadius() {
		return radius;
	}
	
	public void setScale(double input) {
		scale = input;
		setAngle();
	}
	
	public double getScale() {
		return scale;
	}
	
	public void setAngle() {
		point = new APolarPoint(radius*scale, angle);
		width = point.getX();
		height = point.getY();
		line.setWidth(width);
		line.setHeight(height);
	}
	
	public void setAngle(double input) {
		angle = input;
		setAngle();
	}
	
	public double getAngle() {
		return angle;
	}

	public void rotate() {
		angle += Math.PI/reps;
		setAngle();
	}
	
	public void rotate(int reps) {
		while (0 < reps--) {
			rotate();
		}
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
}