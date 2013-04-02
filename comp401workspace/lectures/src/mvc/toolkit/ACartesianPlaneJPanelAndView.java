package mvc.toolkit;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import javax.swing.JPanel;
import composite.objects_shapes.CartesianPlane;
import graphics.Line;
import graphics.StringShape;
import mvc.properties.ObservableCartesianPlane;
import util.models.PropertyListenerRegisterer;

public class ACartesianPlaneJPanelAndView extends JPanel implements BeanView {  
    BasicStroke dotted = new BasicStroke(
          1f, 
          BasicStroke.CAP_ROUND, 
          BasicStroke.JOIN_ROUND, 
          1f, 
          new float[] {2f}, 
          0f);
    ObservableCartesianPlane cartesianPlane;    
    public ACartesianPlaneJPanelAndView(ObservableCartesianPlane aCartesianPlane) {
        cartesianPlane = aCartesianPlane;
        // what if we call register instead?
        cartesianPlane.addPropertyChangeListener(this);
    }
    public void propertyChange(PropertyChangeEvent evt) {
        repaint(); // causes paint to be called
    }   
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(dotted);
        g.setColor(Color.BLUE);     
        draw(g, cartesianPlane);
    }
        
    public  void draw(Graphics g, CartesianPlane aCartesianPlane) {
        draw(g, aCartesianPlane.getXAxis());
        draw(g, aCartesianPlane.getYAxis());
        draw(g, aCartesianPlane.getXLabel());
        draw(g, aCartesianPlane.getYLabel());   
    }   
    public  void draw(Graphics g, Line aLine) {
        g.drawLine(aLine.getX(), aLine.getY(), aLine.getX() + aLine.getWidth(), aLine.getY() + aLine.getHeight());      
    }
    public  void draw(Graphics g, StringShape aLabel) {
        String s = aLabel.getText();
        g.drawString(s, aLabel.getX(), aLabel.getY());      
    }
    @Override
    public void register(PropertyListenerRegisterer aPropertyChangeRegister) {
        aPropertyChangeRegister.addPropertyChangeListener(this);        
    }   
}