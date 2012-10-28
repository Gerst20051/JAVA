package mvc.toolkit;

import java.awt.Component;
import javax.swing.JFrame;
import mvc.properties.AnObservablePlottedShuttle;
import mvc.properties.ObservablePlottedShuttle;
import bus.uigen.ObjectEditor;

public class PlottedShuttleComposer {
    public static void main(String[] args) {        
        ObservablePlottedShuttle shuttleLocation = new AnObservablePlottedShuttle(50, 100);
        BeanView view = new APlottedShuttleJPanelAndView(shuttleLocation);
        JFrame frame = new JFrame("Plotted Shuttle");
        frame.add((Component) view);
        frame.setSize(300, 300);
        frame.setVisible(true);
        ObjectEditor.edit(shuttleLocation); 
        shuttleLocation.setShuttleY(100);
        shuttleLocation.setShuttleX(50);
    }
}