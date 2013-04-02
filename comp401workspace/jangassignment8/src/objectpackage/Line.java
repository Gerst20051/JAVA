package objectpackage;

import java.awt.Color;
import java.awt.Stroke;

public interface Line {
    public int getX();
    public void setX(int newVal);
    public int getY();
    public void setY(int newVal);
    public int getWidth();
    public void setWidth(int newVal);
    public int getHeight();
    public void setHeight(int newVal);
	public Color getColor();
	public void setColor(Color newVal);
	public Stroke getStroke();
	public void setStroke(Stroke newVal);
}