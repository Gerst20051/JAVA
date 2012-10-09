package assignment7;

import java.awt.Color;
import java.awt.Stroke;
import bus.uigen.OEFrame;
import util.annotations.StructurePattern;
import util.annotations.Visible;
import util.misc.ThreadSupport;
@StructurePattern("Bean Pattern")

public class Avatar implements GraphicAvatar {
	OEFrame OE;
	CanvasImageAvatar avatar;
	CanvasText speech = new CanvasText();
	CanvasLine torso = new CanvasLine();
	CanvasAngle arms = new CanvasAngle();
	CanvasAngle legs = new CanvasAngle();

	public Avatar(String input) {
		avatar = new CanvasImageAvatar(input);
		torso.rotate(torso.getReps()/2);
		arms.isOrigin(true);
		legs.isOrigin(true);
		arms.setOriginAngle();
		legs.setOriginAngle();
	}
	
	public void setLocation(int x, int y) {
		double scale = torso.getScale();
		double torso_length = torso.getRadius();
		int image_height = avatar.getHeight(), image_width = avatar.getWidth();
		avatar.setLocation(x, y);
		speech.setLocation(x+image_width+8, y+16);
		torso.setLocation(x+image_width/2, y+image_height);
		arms.setLocation(x+image_width/2, (int) (y+image_height+(torso_length*scale)*0.2));
		legs.setLocation(x+image_width/2, (int) (y+image_height+(torso_length*scale)));
	}
	
	public void moveLocation(int x, int y) {
		x += avatar.getX();
		y += avatar.getY();
		setLocation(x, y);
	}
	
	public void setScale(double input) {
		torso.setScale(input);
		arms.setScale(input);
		legs.setScale(input);
		moveLocation(0, 0);
	}
	
	@Visible(false)
	public Color getColor() {
		return torso.getColor();
	}
	
	public void setColor(Color input) {
		torso.setColor(input);
		arms.setColor(input);
		legs.setColor(input);
	}
	
	@Visible(false)
	public Stroke getStroke() {
		return torso.getStroke();
	}
	
	public void setStroke(Stroke input) {
		torso.setStroke(input);
		arms.setStroke(input);
		legs.setStroke(input);
	}
	
	public CanvasImageAvatar getAvatarImage() {
		return avatar;
	}
	
	public CanvasText getSpeechText() {
		return speech;
	}
	
	public CanvasLine getTorsoLine() {
		return torso;
	}
	
	public CanvasAngle getArmsAngle() {
		return arms;
	}
	
	public CanvasAngle getLegsAngle() {
		return legs;
	}
	
	public void say(String text) {
		speech.setText(text);
	}
	
	public void say(int reps, String[] text) {
		while (0 < reps--) {
			speech.setText(text[(int) Math.floor(Math.random()*text.length)]);
			ThreadSupport.sleep(300);
			OE.refresh();
		}
		ThreadSupport.sleep(300);
		speech.setText("");
		OE.refresh();
	}
	
	public void turnHead(int reps) {
		while (0 < reps--) {
			if (avatar.getOrientation() == "forward") {
				avatar.lookRight();
			} else if (avatar.getOrientation() == "right") {
				avatar.lookBack();
			} else if (avatar.getOrientation() == "back") {
				avatar.lookLeft();
			} else if (avatar.getOrientation() == "left") {
				avatar.lookForward();
			}
			ThreadSupport.sleep(300);
			OE.refresh();
		}
	}
	
	public void animateArms(int input) {
		int direction = 1, reps = arms.getReps(), total = reps*(input*2);
		double oldangle = arms.getAngle();
		arms.setAngle(0);
		for (int i = total; 0 < i; i--) {
			if (i < total) {
				if (i%reps == 0) direction = -direction;
			}
			arms.setAngle(arms.getAngle()+direction*(Math.PI/(reps/2)));
			arms.setOriginAngle();
			ThreadSupport.sleep(25);
			OE.refresh();
		}
		arms.setAngle(oldangle);
		arms.setOriginAngle();
		OE.refresh();
	}
	
	public void animateLegs(int input) {
		int direction = 1, reps = legs.getReps(), total = reps*(input*2);
		double oldangle = legs.getAngle();
		legs.setAngle(0);
		for (int i = total; 0 < i; i--) {
			if (i < total) {
				if (i%reps == 0) direction = -direction;
			}
			legs.setAngle(legs.getAngle()+direction*(Math.PI/reps));
			legs.setOriginAngle();
			ThreadSupport.sleep(25);
			OE.refresh();
		}
		legs.setAngle(oldangle);
		legs.setOriginAngle();
		OE.refresh();
	}
	
	public void rotateLeftArm(int input) {
		int direction = 1, reps = arms.getReps(), total = reps*input;
		double oldangle = arms.getAngle();
		for (int i = total; 0 < i; i--) {
			if (i < total) {
				if (i%(reps/2) == 0) direction = -direction;
			}
			arms.setAngle(arms.getAngle()+direction*(Math.PI/(reps/2)));
			ThreadSupport.sleep(25);
			OE.refresh();
		}
		arms.setAngle(oldangle);
		arms.setOriginAngle();
		OE.refresh();
	}
	
	public void rotateRightArm(int input) {
		int direction = -1, reps = arms.getReps(), total = reps*input;
		arms.isOrigin(false);
		double oldangle = arms.getFirstAngle();
		arms.setFirstAngle(Math.PI/2);
		for (int i = total; 0 < i; i--) {
			if (i < total) {
				if (i%(reps/2) == 0) direction = -direction;
			}
			arms.setFirstAngle(arms.getFirstAngle()+direction*(Math.PI/(reps/2)));
			ThreadSupport.sleep(25);
			OE.refresh();
		}
		arms.isOrigin(true);
		arms.setFirstAngle(oldangle);
		arms.setOriginAngle();
		OE.refresh();
	}
	
	public void rotateLeftLeg(int input) {
		int direction = 1, reps = legs.getReps(), total = reps*input;
		double oldangle = legs.getAngle();
		for (int i = total; 0 < i; i--) {
			if (i < total) {
				if (i%(reps/2) == 0) direction = -direction;
			}
			legs.setAngle(legs.getAngle()+direction*(Math.PI/(reps/2)));
			ThreadSupport.sleep(25);
			OE.refresh();
		}
		legs.setAngle(oldangle);
		legs.setOriginAngle();
		OE.refresh();
	}
	
	public void rotateRightLeg(int input) {
		int direction = -1, reps = legs.getReps(), total = reps*input;
		legs.isOrigin(false);
		double oldangle = arms.getFirstAngle();
		legs.setFirstAngle(Math.PI/2);
		for (int i = total; 0 < i; i--) {
			if (i < total) {
				if (i%(reps/2) == 0) direction = -direction;
			}
			legs.setFirstAngle(legs.getFirstAngle()+direction*(Math.PI/(reps/2)));
			ThreadSupport.sleep(25);
			OE.refresh();
		}
		legs.isOrigin(true);
		legs.setFirstAngle(oldangle);
		legs.setOriginAngle();
		OE.refresh();
	}
	
	public void animateLimbs() {
		rotateLeftArm(2);
		rotateRightArm(2);
		rotateLeftLeg(2);
		rotateRightLeg(2);
	}
	
	public void animateLimbs(int reps) {
		rotateLeftArm(reps);
		rotateRightArm(reps);
		rotateLeftLeg(reps);
		rotateRightLeg(reps);
	}
	
	public void reference(OEFrame object) {
		OE = object;
	}
}