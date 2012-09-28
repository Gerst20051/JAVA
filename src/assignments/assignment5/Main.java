package assignment5;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Main {
	public static void main (String[] args) {
		RotatingLine line = new RotatingLine();
		line.doCircle();
		Angle angle = new Angle();
		OEFrame OE = ObjectEditor.edit(angle);
		angle.reference(OE);
		angle.isOrigin(true);
		angle.setOrigin(Math.PI/2);
		angle.animateOrigin();
		angle.isOrigin(false);
		angle.doCircle();
		Text title = new Text("Wizard of Oz");
		title.display();
		title.rotateText(10);
		Image dorothy = new Image("Dorothy.jpg");
		dorothy.display();
		dorothy.turnHead(10);
		Image background = new Image("Background.jpg");
		background.display();
		background.setSize(650,695);
		Image scarecrow = new Image("Scarecrow.jpg");
		scarecrow.display();
		Image wizard = new Image("Wizard.jpg");
		wizard.display();
	}
}