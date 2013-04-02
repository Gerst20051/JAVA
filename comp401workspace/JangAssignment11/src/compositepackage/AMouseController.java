package compositepackage;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AMouseController implements MouseListener {
	AView view;
	OzScene ozscene;
	int x_target, y_target;
	
	public AMouseController (OzScene theOzScene, AView theview) {
		view = theview;
		ozscene = theOzScene;
		view.addMouseListener(this);
	}
	
	public void mousePressed(MouseEvent arg0) {
		x_target = arg0.getX();
		y_target = arg0.getY();
		ozscene.setX(x_target);
		ozscene.setY(y_target);
	}

	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}
