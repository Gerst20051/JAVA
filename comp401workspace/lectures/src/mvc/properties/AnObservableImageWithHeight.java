package mvc.properties;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import composite.objects_shapes.AnImageWithHeight;

public class AnObservableImageWithHeight extends AnImageWithHeight implements ObservableShuttleImage {
    public AnObservableImageWithHeight(String anImageFileName) {
        super(anImageFileName);
    }

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