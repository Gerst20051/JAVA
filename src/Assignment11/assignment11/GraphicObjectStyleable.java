package assignment11;

import java.awt.Color;
import java.awt.Stroke;

public interface GraphicObjectStyleable {
	public Color getColor();
	public void setColor(Color input);
	public Stroke getStroke();
	public void setStroke(Stroke input);
}