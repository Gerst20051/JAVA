package compositepackage;

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
import objectpackage.StringShape;

import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;


public class AView extends Component implements PropertyChangeListener {
	BasicStroke dotted = new BasicStroke(
	          1f, 
	          BasicStroke.CAP_ROUND, 
	          BasicStroke.JOIN_ROUND, 
	          1f, 
	          new float[] {2f}, 
	          0f);
	OzScene anOzScene;
	Graphics g;
	public AView(OzScene initScene) {
		anOzScene = initScene;
//		anOzScene.addPropertyChangeListener(this);
		//this.setEnabled(true);
		this.setFocusable(true);
	}
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		repaint();
		//anOzScene.addPropertyChangeListener(this);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		//Graphics2D g2 = (Graphics2D) g;
        //g2.setStroke(dotted);
        g.setColor(Color.red); 
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
		draw(g, avatar.getText());
	}
	public void draw(Graphics g, StringShape initstring){
		String string = initstring.getText();
		g.drawString(string, initstring.getX(), initstring.getY());
	}
	public void draw(Graphics g, RotatingLine aline){
		g.drawLine(aline.getX(),aline.getY(), aline.getX() + aline.getWidth(),aline.getY()+aline.getHeight());
	}
	
	public void draw(Graphics g, Angle anangle) {
		//RotatingLine line1 = anangle.getLine1();
		//RotatingLine line2 = anangle.getLine2();
		draw(g, anangle.getLine1());
		draw(g, anangle.getLine2());
	}
	
	public void draw(Graphics g, ImageInterface animage){
		Image img = Toolkit.getDefaultToolkit().getImage(animage.getImageFileName());
		g.drawImage(img, animage.getX(), animage.getY(), this);
	}
	
}
