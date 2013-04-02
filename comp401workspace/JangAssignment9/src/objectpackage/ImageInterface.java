package objectpackage;

import java.beans.PropertyChangeListener;

public interface ImageInterface {
	public int getX();
    public void setX(int newX);
    public int getY();
    public void setY(int newY);
    public String getImageFileName();
    public void addPropertyChangeListener(PropertyChangeListener listener);
}
