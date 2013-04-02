package assignment10;

import java.awt.Color;
import java.awt.BasicStroke;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.Position;
import util.annotations.StructurePattern;
import util.misc.ThreadSupport;
@StructurePattern("Bean Pattern")

public class Canvas implements GraphicCanvas {
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	CanvasImage background = new CanvasImage("Background.jpg");
	Avatar dorothy = new Avatar("Dorothy.jpg");
	Avatar scarecrow = new Avatar("Scarecrow.jpg");
	Avatar wizard = new Avatar("Wizard.jpg");
	Avatar[] avatars = {dorothy, scarecrow, wizard};
	ATable table = new ATable();
	int x_target_coord, y_target_coord;
	
	public Canvas() {

	}
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
	}
	
	@Position(4)
	public CanvasImage getBackgroundImage() {
		assert preGetBackground();
		return background;
	}
	
	@Position(1)
	public Avatar getDorothyAvatar() {
		assert preGetDorothy();
		return dorothy;
	}
	
	@Position(2)
	public Avatar getScarecrowAvatar() {
		assert preGetScarecrow();
		return scarecrow;
	}
	
	@Position(3)
	public Avatar getWizardAvatar() {
		assert preGetWizard();
		return wizard;
	}
	
	public void setX(int input) {
		x_target_coord = input;
		
	}
	
	public void setY(int input) {
		y_target_coord = input;
	}
	
	public void setDorothyPos() {
		getDorothyAvatar().setLocation(x_target_coord, y_target_coord);
	}
	
	public void setScarecrowPos() {
		getScarecrowAvatar().setLocation(x_target_coord, y_target_coord);
	}
	
	public void setWizardPos() {
		getWizardAvatar().setLocation(x_target_coord, y_target_coord);
	}
	
	public void setOriginalPos() {
		setLocations();
	}
	
	public void setTable() {
		table.put("background", true);
		table.put("dorothy", true);
		table.put("scarecrow", true);
		table.put("wizard", true);
	}
	
	public boolean preGetBackground() {
		return (boolean) table.get("background");
	}
	
	public boolean preGetDorothy() {
		return (boolean) table.get("dorothy");
	}
	
	public boolean preGetScarecrow() {
		return (boolean) table.get("scarecrow");
	}
	
	public boolean preGetWizard() {
		return (boolean) table.get("wizard");
	}
	
	public boolean preBackgroundEnters() {
		return (boolean) table.get("background");
	}
	
	public boolean preBackgroundLeaves() {
		return (boolean) table.get("background");
	}
	
	public boolean preDorothyEnters() {
		return (boolean) table.get("dorothy");
	}
	
	public boolean preDorothyLeaves() {
		return (boolean) table.get("dorothy");
	}
	
	public boolean preScarecrowEnters() {
		return (boolean) table.get("scarecrow");
	}
	
	public boolean preScarecrowLeaves() {
		return (boolean) table.get("scarecrow");
	}
	
	public boolean preWizardEnters() {
		return (boolean) table.get("wizard");
	}
	
	public boolean preWizardLeaves() {
		return (boolean) table.get("wizard");
	}
	
	public void backgroundEnters() {
		assert preBackgroundEnters();
		table.put("background",true);
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "this", "Background", true));
	}
	
	public void backgroundLeaves() {
		assert preBackgroundLeaves();
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "this", "Background", false));
		table.put("background", false);
	}
	
	public void dorothyEnters() {
		assert preDorothyEnters();
		table.put("dorothy",true);
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "this", "Dorothy", true));
	}
	
	public void dorothyLeaves() {
		assert preDorothyLeaves();
		table.put("dorothy", false);
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "this", "Dorothy", false));			
	}
	
	public void scarecrowEnters() {
		assert preScarecrowEnters();
		table.put("scarecrow", true);
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "this", "Scarecrow", true));
	}
	
	public void scarecrowLeaves() {
		assert preScarecrowLeaves();
		table.put("scarecrow", false);
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "this", "Scarecrow", false));
	}
	
	public void wizardEnters() {
		assert preWizardEnters();
		table.put("wizard", true);
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "this", "Wizard", true));			
	}
	
	public void wizardLeaves() {
		assert preWizardLeaves();
		table.put("wizard", false);
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "this", "Wizard", false));
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
		ThreadSupport.sleep(1000);
		wizard.say("");
		dorothy.say("Hi, I'm Dorothy!");
		ThreadSupport.sleep(1000);
		dorothy.say("");
		scarecrow.say("Hi, I'm Scarecrow!");
		ThreadSupport.sleep(1000);
		scarecrow.say("");
	}
	
	private void scrollScene(int x, int y) {
		double time = 400, delay = 20, reps = time/delay;
		x = (int) (x/reps);
		y = (int) (y/reps);
		for (int i = (int) reps; 0 < i; i--) {
			dorothy.moveLocation(x, y);
			scarecrow.moveLocation(x, y);
			wizard.moveLocation(x, y);
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
			ThreadSupport.sleep((long) delay);
		}
	}
	
	private void animateScene() {
		dorothy.say("Hang on for the ride!");
		scrollScene(100,100,2);
		scrollScene(0,-200,.3);
		scrollScene(-300,0);
		scrollScene(0,250);
		scrollScene(245,150);
		scrollScene(0,-305,3);
		dorothy.say("WOW. That was fun!");
		ThreadSupport.sleep(2000);
		dorothy.say("");
	}
	
	private void doDance(Avatar resource) {
		resource.moveLocation(-20,-20);
		ThreadSupport.sleep(300);
		resource.moveLocation(40,0);
		ThreadSupport.sleep(300);
		resource.moveLocation(0,40);
		ThreadSupport.sleep(300);
		resource.moveLocation(-40,0);
		ThreadSupport.sleep(300);
		resource.moveLocation(20,-20);
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
	}
	
	public void init() {
		setTable();
		setLocations();
		styleLines();
		//animate();
	}
}