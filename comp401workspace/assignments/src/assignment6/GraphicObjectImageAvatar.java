package assignment6;

public interface GraphicObjectImageAvatar extends GraphicObject, GraphicObjectDisplayable, GraphicObjectImage {
	public void setOrientation(String input);
	public String getOrientation();
	public void lookLeft();
	public void lookForward();
	public void lookRight();
	public void lookBack();
}