package composite.objects_shapes;

import graphics.Line;
import graphics.StringShape;

public interface CartesianPlane {
    public int getAxesLength();
    public void setAxesLength(int anAxesLength);
    public Line getXAxis();
    public Line getYAxis();
    public StringShape getXLabel();
    public StringShape getYLabel();
}