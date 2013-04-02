package assignment11;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AMouseController implements MouseListener {
	Canvas canvas;
	AView view;
	
	public AMouseController(Canvas initCanvas, AView initView) {
		canvas = initCanvas;
		view = initView;
		view.addMouseListener(this);
	}
	
	public void mousePressed(MouseEvent arg0) {
		int x_coord = arg0.getX();
		int y_coord = arg0.getY();
		// arg0.getPoint();
		canvas.setX(x_coord);
		canvas.setY(y_coord);
	}

	public void mouseClicked(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}