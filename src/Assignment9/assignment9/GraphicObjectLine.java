package assignment9;

public interface GraphicObjectLine extends GraphicObject, GraphicObjectRotatable, GraphicObjectStyleable, GraphicObjectScalable {
	public void setAngle();
	public void setAngle(double input);
	public double getAngle();
	public void rotate();
	public void rotate(int reps);
}