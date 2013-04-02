package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.sound.sampled.Line;

import objectpackage.ARotatingLine;
import objectpackage.ImageInterface;
import objectpackage.RotatingLine;

import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;

import compositepackage.AnAngle;
import compositepackage.AnAvatar;
import compositepackage.Angle;
import compositepackage.Avatar;
import compositepackage.OzScene;

public class AView extends Component implements PropertyChangeListener {
	BasicStroke dotted = new BasicStroke(
	          1f, 
	          BasicStroke.CAP_ROUND, 
	          BasicStroke.JOIN_ROUND, 
	          1f, 
	          new float[] {2f}, 
	          0f);
	OzScene anOzScene;
	
	PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
	}

	public AView(OzScene initScene) {
		anOzScene = initScene;
	}
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		repaint();
	}
	
	public void paint(Graphics g, OzScene anOzScene) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(dotted);
        g.setColor(Color.BLUE); 
        draw(g, anOzScene);
	
	}

	public void draw(Graphics g, OzScene ozscene){
		draw(g, ozscene.getBackground());
		draw(g, ozscene.getDorothy());
		draw(g, ozscene.getScarecrow());
		draw(g, ozscene.getOz());
	}
	public void draw(Graphics g, Avatar avatar) {
		draw(g, avatar.getHead());
		draw(g, avatar.getArms());
		draw(g, avatar.getTorso());
		draw(g, avatar.getLegs());
		
	}
	
	public void draw(Graphics g, RotatingLine aline){
		g.drawLine(aline.getX(),aline.getY(), aline.getX() + aline.getWidth(),aline.getY()+aline.getHeight());
	}
	
	public void draw(Graphics g, Angle anangle) {
		RotatingLine line1 = anangle.getLine1();
		RotatingLine line2 = anangle.getLine2();
		draw(g, line1);
		draw(g, line2);
	}
	
	public void draw(Graphics g, ImageInterface animage){
		Image img = Toolkit.getDefaultToolkit().getImage(animage.getImageFileName());
		g.drawImage(img, animage.getX(), animage.getY(), this);
	}
	
}
