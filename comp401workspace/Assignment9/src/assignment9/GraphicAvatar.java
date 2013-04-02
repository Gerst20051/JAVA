package assignment9;

import java.awt.Color;
import java.awt.Stroke;

public interface GraphicAvatar {
	public void setLocation(int x, int y);
	public void moveLocation(int x, int y);
	public void setScale(double input);
	public Color getColor();
	public void setColor(Color input);
	public Stroke getStroke();
	public void setStroke(Stroke input);
	public CanvasImageAvatar getAvatarImage();
	public CanvasText getSpeechText();
	public CanvasLine getTorsoLine();
	public CanvasAngle getArmsAngle();
	public CanvasAngle getLegsAngle();
	public void say(String text);
	public void say(String text, int secs);
	public void say(int reps, String[] text);
	public void turnHead(int reps);
	public void animateArms(int input);
	public void animateLegs(int input);
	public void rotateLeftArm(int input);
	public void rotateRightArm(int input);
	public void rotateLeftLeg(int input);
	public void rotateRightLeg(int input);
	public void animateLimbs();
	public void animateLimbs(int reps);
}