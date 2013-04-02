package assignment11;

public interface GraphicObjectAngle extends GraphicObject, GraphicObjectRotatable, GraphicObjectStyleable, GraphicObjectScalable {
	public void isOrigin(boolean value);
	public void setOrigin(double value);
	public double getOrigin();
	public void setOriginAngle();
	public void setAngle();
	public void setAngle(double input);
	public double getAngle();
	public void setFirstAngle();
	public void setFirstAngle(double firstangle);
	public double getFirstAngle();
	public Line getFirstLine();
	public Line getSecondLine();
	public void rotate();
	public void rotate(int reps);
	public void rotateSecond();
	public void rotateSecond(int reps);
}