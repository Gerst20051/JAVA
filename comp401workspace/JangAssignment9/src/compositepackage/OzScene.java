package compositepackage;

import java.beans.PropertyChangeListener;

import objectpackage.ImageInterface;

public interface OzScene{
	public ImageInterface getBackground();
	public Avatar getDorothy();
	public Avatar getScarecrow();
	public Avatar getOz();
	public void setX(int initX);
	public void setY(int initY);
	public void setDorothyXY();
	public void setScarecrowXY();
	public void setOZXY();
	public void setOriginalXY();
	public void addPropertyChangeListener(PropertyChangeListener listener);
}
