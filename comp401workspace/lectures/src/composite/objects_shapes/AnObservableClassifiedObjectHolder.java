package composite.objects_shapes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import mvc.properties.APropertyListenerSupport;
import mvc.properties.PropertyListenerSupport;
import util.annotations.ComponentWidth;
import util.models.PropertyListenerRegisterer;

public class AnObservableClassifiedObjectHolder implements PropertyListenerRegisterer, ClassifiedObjectHolder {
    Object object;
    String kind;
    PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();   
    @Override
    public Object getObject() {
        return object;
    }
    @Override
    public void setObject(Object newVal) {
        Object oldVal = object;
        object = newVal;
        propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Object", oldVal, newVal));       
    }
    @Override
    @ComponentWidth(285)
    public String getKind() {
        return kind;
    }

    @Override
    public void setKind(String newVal) {
        String oldVal = kind;
        kind = newVal;
        propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Kind", oldVal, newVal));
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener aListener) {
        propertyListenerSupport.add(aListener);
    }
}