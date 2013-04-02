package composite.objects_shapes;

import mvc.properties.ObjectHolder;

public class AnObjectHolder implements ObjectHolder {
    Object object;
    @Override
    public Object getObject() {
        return object;
    }
    @Override
    public void setObject(Object newVal) {
        object = newVal;        
    }
}