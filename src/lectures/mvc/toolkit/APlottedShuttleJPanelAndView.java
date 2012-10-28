package mvc.toolkit;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import composite.objects_shapes.PlottedShuttle;
import composite.objects_shapes.ImageWithHeight;
import mvc.properties.ObservableCartesianPlane;
import mvc.properties.ObservablePlottedShuttle;

public class APlottedShuttleJPanelAndView extends ACartesianPlaneJPanelAndView implements BeanView {   
    ObservablePlottedShuttle plottedShuttle;    
    public APlottedShuttleJPanelAndView(ObservablePlottedShuttle aPlottedShuttle) {
        // can we get rid of the cast while still doing reuse?
        super((ObservableCartesianPlane) aPlottedShuttle.getCartesianPlane());
        plottedShuttle = aPlottedShuttle;
        // why do we need this if the superclass is also doing a registration?
        // how will this change if superclass constructor calls register?
        plottedShuttle.addPropertyChangeListener(this);
    }       
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        draw(g2, plottedShuttle.getShuttleImage());
    }
    public void draw(Graphics2D g, ImageWithHeight anImage) {
        Image img = Toolkit.getDefaultToolkit().getImage(anImage.getImageFileName());
        g.drawImage(img, anImage.getX(), anImage.getY(), this);     
    }   
    public void draw(Graphics2D g, PlottedShuttle aShuttleLocation) {
        draw(g, aShuttleLocation.getCartesianPlane());
        draw(g, aShuttleLocation.getShuttleImage());
    }
}