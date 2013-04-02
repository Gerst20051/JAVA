package assignment7;

import java.awt.Color;
import java.awt.BasicStroke;
import bus.uigen.OEFrame;
import util.annotations.Position;
import util.annotations.StructurePattern;
import util.misc.ThreadSupport;
@StructurePattern("Bean Pattern")

public class Canvas implements GraphicCanvas {
	OEFrame OE;
	CanvasImage background = new CanvasImage("Background.jpg");
	Avatar dorothy = new Avatar("Dorothy.jpg");
	Avatar scarecrow = new Avatar("Scarecrow.jpg");
	Avatar wizard = new Avatar("Wizard.jpg");
	Avatar[] avatars = {dorothy, scarecrow, wizard};
	
	public Canvas() {

	}
	
	@Position(4)
	public CanvasImage getBackgroundImage() {
		return background;
	}
	
	@Position(1)
	public Avatar getDorothyAvatar() {
		return dorothy;
	}
	
	@Position(2)
	public Avatar getScarecrowAvatar() {
		return scarecrow;
	}
	
	@Position(3)
	public Avatar getWizardAvatar() {
		return wizard;
	}
	
	private void setReferences() {
		dorothy.reference(OE);
		scarecrow.reference(OE);
		wizard.reference(OE);
	}
	
	private void setLocations() {
		dorothy.setLocation(250,250);
		scarecrow.setLocation(400,400);
		wizard.setLocation(100,100);
	}
	
	private void styleLines() {
		dorothy.setColor(Color.BLACK);
		dorothy.setStroke(new BasicStroke(2.0f));
		scarecrow.setColor(Color.BLUE);
		scarecrow.setStroke(new BasicStroke(7.0f));
		wizard.setColor(Color.RED);
		wizard.setStroke(new BasicStroke(5.0f));
	}
	
	private void scaleAvatars() {
		dorothy.setScale(3);
		scarecrow.setScale(0.65);
		wizard.setScale(2);
	}
	
	private void doIntroductions() {
		wizard.say("Hi, I'm Oz!");
		OE.refresh();
		ThreadSupport.sleep(1000);
		wizard.say("");
		dorothy.say("Hi, I'm Dorothy!");
		OE.refresh();
		ThreadSupport.sleep(1000);
		dorothy.say("");
		scarecrow.say("Hi, I'm Scarecrow!");
		OE.refresh();
		ThreadSupport.sleep(1000);
		scarecrow.say("");
		OE.refresh();
	}
	
	private void scrollScene(int x, int y) {
		double time = 400, delay = 20, reps = time/delay;
		x = (int) (x/reps);
		y = (int) (y/reps);
		for (int i = (int) reps; 0 < i; i--) {
			dorothy.moveLocation(x, y);
			scarecrow.moveLocation(x, y);
			wizard.moveLocation(x, y);
			OE.refresh();
			ThreadSupport.sleep((long) delay);
		}
	}
	
	private void scrollScene(int x, int y, double time) {
		time *= 1000;
		double delay = 20, reps = time/delay;
		x = (int) (x/reps);
		y = (int) (y/reps);
		for (int i = (int) reps; 0 < i; i--) {
			dorothy.moveLocation(x, y);
			scarecrow.moveLocation(x, y);
			wizard.moveLocation(x, y);
			OE.refresh();
			ThreadSupport.sleep((long) delay);
		}
	}
	
	private void animateScene() {
		dorothy.say("Hang on for the ride!");
		OE.refresh();
		scrollScene(100,100,2);
		scrollScene(0,-200,.3);
		scrollScene(-300,0);
		scrollScene(0,250);
		scrollScene(245,150);
		scrollScene(0,-305,3);
		dorothy.say("WOW. That was fun!");
		OE.refresh();
		ThreadSupport.sleep(2000);
		dorothy.say("");
		OE.refresh();
	}
	
	private void doDance(Avatar resource) {
		resource.moveLocation(-20,-20);
		OE.refresh();
		ThreadSupport.sleep(300);
		resource.moveLocation(40,0);
		OE.refresh();
		ThreadSupport.sleep(300);
		resource.moveLocation(0,40);
		OE.refresh();
		ThreadSupport.sleep(300);
		resource.moveLocation(-40,0);
		OE.refresh();
		ThreadSupport.sleep(300);
		resource.moveLocation(20,-20);
		OE.refresh();
	}
	
	private void animateLimbs(Avatar resource) {
		resource.animateLimbs();
	}
	
	private void animateLimbs(Avatar resource, int reps) {
		resource.animateLimbs(reps);
	}
	
	public void animate() {
		scaleAvatars();
		doIntroductions();
		animateScene();
		dorothy.turnHead(6);
		dorothy.animateLegs(3);
		String[] scarecrow_speech = {"Wizard of Oz","Random String","Yay!","Interesting!","Last Text String in Array"};
		scarecrow.say(10,scarecrow_speech);
		doDance(avatars[(int) Math.floor((double) (Math.random()*avatars.length))]);
		animateLimbs(avatars[(int) Math.floor((double) (Math.random()*avatars.length))]);
		wizard.animateArms(2);
		animateLimbs(dorothy, 1);
		dorothy.getAvatarImage().lookForward();
		OE.refresh();
	}
	
	public void init() {
		setReferences();
		setLocations();
		styleLines();
		OE.refresh();
	}
	
	public void reference(OEFrame object) {
		OE = object;
	}
}