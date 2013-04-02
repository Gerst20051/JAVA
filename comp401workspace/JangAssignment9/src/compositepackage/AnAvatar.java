package compositepackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import objectpackage.AStringShape;
import objectpackage.ImageInterface;
import objectpackage.RotatingLine;
import objectpackage.StringShape;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.Visible;


public class AnAvatar implements Avatar{
	int x,y; 
	ImageInterface newHead;
	StringShape string;
	RotatingLine torso;
	Angle arms, legs;
	
	public AnAvatar(int initX, int initY, ImageInterface initHead, RotatingLine initTorso, Angle initarms, Angle initlegs){
		newHead = initHead;
		x = initX;
		y = initY;
		torso = initTorso;
		arms = initarms;
		legs = initlegs; 
		string = new AStringShape ("hi",initX, initY-10);
	}
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.getArms().getLine1().addPropertyChangeListener(listener);
		this.getArms().getLine2().addPropertyChangeListener(listener);
		this.getLegs().getLine1().addPropertyChangeListener(listener);
		this.getLegs().getLine2().addPropertyChangeListener(listener);
		this.getText().addPropertyChangeListener(listener);
		this.getTorso().addPropertyChangeListener(listener);
		this.getHead().addPropertyChangeListener(listener);
	}

	@Visible(true)
	public StringShape getText(){
		return string; 
	}
	
	public void setText(String input){
		string.setText(input);
	}
	
	public void setHead(ImageInterface initHead){
		newHead = initHead;
	}
	public ImageInterface getHead(){
		return newHead;
	}
	public Angle getArms(){
		return arms; 
	}
	public Angle getLegs(){
		return legs; 
	}
	public RotatingLine getTorso(){
		return torso;
	}

	public void setX(int newX) {
		x = newX;
		assemble();
	}
//	@Visible(false)
	public int getX() {
		return x;
	}

	public void setY(int newY) {
		y = newY;
		assemble();
	}
//	@Visible(false)
	public int getY() {
		return y;
	}
	private void assemble(){
		string.setX(x);
		string.setY(y);
		newHead.setX(x);
		newHead.setY(y);
		torso.setX(x+40);
		torso.setY(y+80);
		arms.setX(x+40);
		arms.setY(y+80);
		legs.setX(x+40);
		legs.setY(y+120);
	}

}
