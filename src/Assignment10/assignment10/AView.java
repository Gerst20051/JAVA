package assignment10;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AView extends Component implements PropertyChangeListener {
	BasicStroke dotted = new BasicStroke(
		1f, 
		BasicStroke.CAP_ROUND, 
		BasicStroke.JOIN_ROUND, 
		1f, 
		new float[] {2f}, 
		0f
	);
	Canvas canvas;
	Graphics g;
	
	public AView(Canvas initCanvas) {
		canvas = initCanvas;
		canvas.addPropertyChangeListener(this);
		this.setFocusable(true);
	}
	
	public void propertyChange(PropertyChangeEvent arg0) {
		repaint();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.RED); 
		draw(g, canvas);
	}

	public void draw(Graphics g, Canvas ozCanvas) {
		draw(g, ozCanvas.getBackgroundImage());
		draw(g, ozCanvas.getDorothyAvatar());
		draw(g, ozCanvas.getScarecrowAvatar());
		draw(g, ozCanvas.getWizardAvatar());
	}
	
	public void draw(Graphics g, Avatar avatar) {
		draw(g, avatar.getAvatarImage());
		draw(g, avatar.getSpeechText());
		draw(g, avatar.getTorsoLine());
		draw(g, avatar.getArmsAngle());
		draw(g, avatar.getLegsAngle());
	}
	
	public void draw(Graphics g, CanvasText string) {
		draw(g, string.getText());
	}
	
	public void draw(Graphics g, StringShape string) {
		g.drawString(string.getText(), string.getX(), string.getY());
	}
	
	public void draw(Graphics g, CanvasLine line) {
		draw(g, line.getLine());
	}
	
	public void draw(Graphics g, Line line) {
		g.drawLine(line.getX(), line.getY(), line.getX() + line.getWidth(), line.getY() + line.getHeight());
	}
	
	public void draw(Graphics g, CanvasAngle angle) {
		draw(g, angle.getFirstLine());
		draw(g, angle.getSecondLine());
	}
	
	public void draw(Graphics g, CanvasImage image) {
		Image img = Toolkit.getDefaultToolkit().getImage(image.getImageFileName());
		g.drawImage(img, image.getX(), image.getY(), this);
	}
}