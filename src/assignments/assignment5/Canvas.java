package assignment5;

import bus.uigen.OEFrame;
import util.annotations.Position;
import util.annotations.StructurePattern;
import util.misc.ThreadSupport;
@StructurePattern("Bean Pattern")

public class Canvas {
	OEFrame OE;
	CanvasImage background = new CanvasImage("Background.jpg");
	CanvasImage dorothy = new CanvasImage("Dorothy.jpg");
	CanvasImage scarecrow = new CanvasImage("Scarecrow.jpg");
	CanvasImage wizard = new CanvasImage("Wizard.jpg");
	CanvasText title = new CanvasText();
	CanvasRotatingLine line = new CanvasRotatingLine();
	CanvasAngle angle = new CanvasAngle();
	
	@Position(7)
	public CanvasImage getBackgroundImage() {
		return background;
	}
	
	@Position(1)
	public CanvasImage getDorothyImage() {
		return dorothy;
	}
	
	@Position(2)
	public CanvasImage getScarecrowImage() {
		return scarecrow;
	}
	
	@Position(3)
	public CanvasImage getWizardImage() {
		return wizard;
	}
	
	@Position(4)
	public CanvasText getTitleText() {
		return title;
	}
	
	@Position(5)
	public CanvasRotatingLine getRotatingLine() {
		return line;
	}
	
	@Position(6)
	public CanvasAngle getAngleLine() {
		return angle;
	}
	
	public void rotateLine(int reps) {
		while (0 < reps--) {
			line.setLocation(reps+100, reps+100);
			line.rotate();
			ThreadSupport.sleep(100);
			OE.refresh();
		}
	}
	
	public void doLineCircle() {
		line.setLocation(100, 100);
		rotateLine(2*line.getReps());
	}
	
	public void animateAngleOrigin() {
		int direction = 1, reps = angle.getReps(), total = reps*9;
		double oldangle = angle.getAngle();
		angle.setAngle(0);
		angle.setLocation(150, 100);
		for (int i = total; 0 < i; i--) {
			if (i < total) {
				if (i%reps == 0) direction = -direction;
				if (i%reps*2 == 0) angle.setOrigin(angle.getOrigin()+Math.PI/4);
			}
			angle.setAngle(angle.getAngle()+direction*(Math.PI/reps));
			angle.setOriginAngle();
			ThreadSupport.sleep(25);
			OE.refresh();
		}
		angle.setAngle(oldangle);
	}
	
	public void rotateAngle(int reps) {
		while (0 < reps--) {
			angle.setLocation(reps+100, reps+100);
			angle.rotate();
			ThreadSupport.sleep(100);
			OE.refresh();
		}
	}
	
	public void doAngleCircle() {
		angle.setLocation(100, 100);
		rotateAngle(2*angle.getReps());
	}
	
	public void rotateSecondAngle(int reps) {
		while (0 < reps--) {
			angle.setLocation(reps+100, reps+100);
			angle.rotateSecond();
			ThreadSupport.sleep(100);
			OE.refresh();
		}
	}
	
	public void doCircleSecondAngle() {
		angle.setLocation(100, 100);
		rotateSecondAngle(2*angle.getReps());
	}
	
	public void rotateText(int reps) {
		String[] text = {"Wizard of Oz","Random String","Yay!","Interesting!","Last Text String in Array"};
		while (0 < reps--) {
			title.setLocation(reps+100, reps+100);
			title.setText(text[(int) Math.floor(Math.random()*text.length)]);
			ThreadSupport.sleep(300);
			OE.refresh();
		}
	}
	
	public void turnHead(CanvasImage resource, int reps) {
		while (0 < reps--) {
			resource.setLocation(reps+100, reps+100);
			if (resource.getOrientation() == "forward") {
				resource.lookRight();
			} else if (resource.getOrientation() == "right") {
				resource.lookBack();
			} else if (resource.getOrientation() == "back") {
				resource.lookLeft();
			} else if (resource.getOrientation() == "left") {
				resource.lookForward();
			}
			ThreadSupport.sleep(300);
			OE.refresh();
		}
	}
	
	public void animate() {
		doLineCircle();
		angle.isOrigin(true);
		angle.setOrigin(Math.PI/2);
		animateAngleOrigin();
		angle.isOrigin(false);
		doAngleCircle();
		rotateText(10);
		turnHead(dorothy, 10);
	}
	
	public void reference(OEFrame object) {
		OE = object;
	}
}