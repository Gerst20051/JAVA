package assignment7;

public interface Label {
	public Point getLocation();
	public void setLocation(Point newVal);
	public int getWidth();
	public void setWidth(int newVal);
	public void setHeight(int newVal);
	public String getText();
	public void setText(String newVal);
	public String getImageFileName();
	public void setImageFileName(String newVal);
}