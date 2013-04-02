package compositepackage;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AKeyController implements KeyListener {
	AView view;
	OzScene ozscene;
	
	public AKeyController(OzScene theOzScene, AView theView)
	{
		ozscene = theOzScene;
		view = theView;
		view.addKeyListener(this);
		view.setFocusable(true);
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		char typedChar = e.getKeyChar();
		switch (typedChar) {
		case 'd':
			ozscene.setDorothyXY();
			break;
		case 's':
			ozscene.setScarecrowXY();
			break;
		case 'o':
			ozscene.setOZXY();
			break;
		case 'r':
			ozscene.setOriginalXY();
			break;

		}

	}
}
