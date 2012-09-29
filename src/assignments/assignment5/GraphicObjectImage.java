package assignment5;

public interface GraphicObjectImage {
	public void setSize(int width, int height);
	public void lookLeft();
	public void lookForward();
	public void lookRight();
	public void lookBack();
	public void turnHead(int reps);
}