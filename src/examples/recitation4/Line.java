package recitation4;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.LINE_PATTERN)

public interface Line {
	public int getX();
	public void setX(int newX);
	public int getY();
	public void setY(int newY);
	public int getWidth();
	public void setWidth(int newWidth);
	public int getHeight() ;
	public void setHeight(int newHeight);
}