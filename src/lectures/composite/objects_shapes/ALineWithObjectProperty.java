package composite.objects_shapes;

import graphics.ACartesianPoint;
import graphics.Point;

public class ALineWithObjectProperty implements LineWithObjectProperty {
    int width, height;
    Point location;
    public ALineWithObjectProperty(
        Point initLocation, int initWidth, int initHeight) {
        location = initLocation;
        width = initWidth;
        height = initHeight;    
    }
    public Point getLocation() {return location;}
    public void setLocation(Point newVal) {location = newVal;}
    public int getWidth() {return width;}
    public void setWidth(int newVal) {width = newVal;}
    public int getHeight() {return height;}
    public void setHeight(int newHeight) {height = newHeight;}  
    public static void main(String args[]) {
        bus.uigen.ObjectEditor.edit (new ALineWithObjectProperty(new ACartesianPoint(10, 10), 20, 20));
    }
}