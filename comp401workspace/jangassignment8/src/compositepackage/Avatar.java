package compositepackage;

import objectpackage.ImageInterface;
import objectpackage.RotatingLine;
import objectpackage.StringShape;

public interface Avatar{
	public StringShape getText();
	public void setText(String input);
	public ImageInterface getHead();
	public Angle getArms();
	public Angle getLegs();
	public RotatingLine getTorso();
	
}
