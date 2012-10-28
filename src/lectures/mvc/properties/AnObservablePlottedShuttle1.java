package mvc.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import composite.objects_shapes.APlottedShuttle;
import bus.uigen.ObjectEditor;

public class AnObservablePlottedShuttle1 extends APlottedShuttle implements ObservablePlottedShuttle {
    PropertyListenerSupport propertySupport = new APropertyListenerSupport();   
    public AnObservablePlottedShuttle1(int anX, int aY) {
        super(anX, aY);
        cartesianPlane = new AnObservableCartesianPlane(AXES_LENGTH, ORIGIN_X, ORIGIN_Y);
        shuttleImage = new AnObservableShuttleImage();
//      propertySupport = new APropertyListenerSupport();   
        setShuttleX(anX);
        setShuttleY(aY);
    }
    public void setShuttleX(int newVal) {
        int oldVal = getShuttleX();
        super.setShuttleX(newVal);
        if (propertySupport != null) //why this check? Can we change super class so this check is not made
          propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "ShuttleX", oldVal, newVal));
    }
    public void setShuttleY(int newVal) {
        int oldVal = getShuttleY();
        super.setShuttleY(newVal);
        if (propertySupport != null) //why this check?
          propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "ShuttleY", oldVal, newVal));
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.add(listener);      
    }
    public static void main (String[] args) {
        ObservablePlottedShuttle shuttleLocation = new AnObservablePlottedShuttle(50, 100); 
        ObjectEditor.edit(shuttleLocation);
        shuttleLocation.setShuttleY(50);
        shuttleLocation.setShuttleX(100);
    }
}