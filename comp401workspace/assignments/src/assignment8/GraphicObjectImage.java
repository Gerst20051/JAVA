package assignment8;

public interface GraphicObjectImage extends GraphicObject, GraphicObjectDisplayable {
	public ImageShape getImage();
	public int getHeight();
	public int getWidth();
	public int getX();
	public int getY();
}