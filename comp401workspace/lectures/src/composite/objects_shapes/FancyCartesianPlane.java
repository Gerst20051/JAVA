package composite.objects_shapes;

import graphics.StringShape;

public interface FancyCartesianPlane {
    public int getAxesLength();
    public void setAxesLength(int newVal);
    public StringShape getXLabel();
    public StringShape getYLabel();
    public CartesianPlane getCartesianPlane();
}