package assignment6;

import java.awt.Color;
import java.awt.Stroke;
import bus.uigen.OEFrame;

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
	public void say(int reps, String[] text);
	public void turnHead(int reps);
	public void animateArms(int input);
	public void animateLegs(int input);
	public void reference(OEFrame object);
}