package mvc.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import composite.objects_shapes.ACartesianPlane;
import composite.objects_shapes.CartesianPlane;
import bus.uigen.ObjectEditor;

public class AnObservableCartesianPlane extends ACartesianPlane implements ObservableCartesianPlane {
//    Line xAxis;
//    Line yAxis;  
    PropertyListenerSupport propertySupport = new APropertyListenerSupport();
    public AnObservableCartesianPlane (int theAxesLength, int theOriginX, int theOriginY ) {
        super(theAxesLength, theOriginX, theOriginY);
        xAxis = new AnObservableLine(toXAxisX(), toXAxisY(), axesLength, 0);
        yAxis = new AnObservableLine(toYAxisX(), toYAxisY(), 0, axesLength); 
        xLabel = new AnObservableStringShape("X", toXLabelX(), toXLabelY());
        yLabel = new AnObservableStringShape("Y", toYLabelX(), toYLabelY());
    } 

    public void setAxesLength(int newVal) {
        int oldVal = getAxesLength();
        super.setAxesLength(newVal);
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "axesLength", oldVal, newVal));
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.add(listener);
    }
    public static void main (String[] args) {
        CartesianPlane plane = new AnObservableCartesianPlane(200, 125, 125);
        ObjectEditor.edit(plane);
    }   
}