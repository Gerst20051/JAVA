package mvc.toolkit;

import java.awt.Component;
import javax.swing.JFrame;
import mvc.properties.AnObservableCartesianPlane;
import mvc.properties.ObservableCartesianPlane;
import bus.uigen.ObjectEditor;

public class CartesianPlaneComposer {
    public static void main(String[] args) {        
        ObservableCartesianPlane cartesianPlane = new AnObservableCartesianPlane(100, 200, 200);        
        BeanView view = new ACartesianPlaneJPanelAndView(cartesianPlane);
        // why not add it in the composer
//      ((PropertyListenerRegisterer) cartesianPlane).addPropertyChangeListener(view);
        JFrame frame = new JFrame("Cartesian Plane");
        // can we get rid of the cast somehow?
        frame.add((Component) view);
        frame.setSize(300, 300);
        frame.setVisible(true);
        ObjectEditor.edit(cartesianPlane);          
    }
}