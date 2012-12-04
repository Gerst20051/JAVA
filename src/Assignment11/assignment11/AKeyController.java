package assignment11;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AKeyController implements KeyListener {
	AView view;
	Canvas canvas;
	
	public AKeyController(Canvas initCanvas, AView initView) {
		canvas = initCanvas;
		view = initView;
		view.addKeyListener(this);
		view.setFocusable(true);
	}
	
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {
		char typedChar = e.getKeyChar();
		switch (typedChar) {
			case 'd':
				canvas.setDorothyPos();
				break;
			case 's':
				canvas.setScarecrowPos();
			break;
			case 'o':
				canvas.setWizardPos();
			break;
			case 'r':
				canvas.setOriginalPos();
			break;
		}
	}
}