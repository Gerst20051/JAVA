package recitation4;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.RECTANGLE_PATTERN)

public interface Rectangle {
	public int getX();
	public void setX(int x);
	public int getY();
	public void setY(int y);
	public int getWidth();
	public void setWidth(int width);
	public int getHeight();
	public void setHeight(int height);
}