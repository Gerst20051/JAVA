package mvc.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import composite.objects_shapes.AShuttleImage;

public class AnObservableShuttleImage extends AShuttleImage implements ObservableShuttleImage {
    PropertyListenerSupport propertySupport = new APropertyListenerSupport();

     public void setX(int newVal) {
        int oldVal = getX();
        super.setX(newVal);
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "X", oldVal,
                newVal));
    }
    public void setY(int newVal) {
        int oldVal = getY();
        super.setY(newVal);
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Y", oldVal,
                newVal));
    }   
    
     public void addPropertyChangeListener(PropertyChangeListener l) {
        propertySupport.add(l);
    }
}