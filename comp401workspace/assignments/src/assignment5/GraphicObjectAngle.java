package assignment5;

public interface GraphicObjectAngle {
	public void isOrigin(boolean value);
	public void setOrigin(double value);
	public void animateOrigin();
	public void setOriginAngle();
	public void setAngle();
	public void setAngle(double input);
	public void setFirstAngle(double firstangle);
	public Line getFirstLine();
	public Line getSecondLine();
	public void rotate();
	public void rotate(int reps);
	public void doCircle();
	public void rotateSecond();
	public void rotateSecond(int reps);
	public void doCircleSecond();
}