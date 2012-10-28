package composite.objects_shapes;

import graphics.Line;

public interface CartesianPlaneWithNullAxis {
    public Line getXAxis();
    public Line getYAxis();
    public void toggleXAxis();
}