package assignment7;

import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class Scene {
	OEFrame OE;
	Canvas canvas = new Canvas();
	Scanner scanner = new Scanner("");
	Parser parser = new Parser(canvas, scanner);

	public Scene() {
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	private void animateScene() {
		String[] animate = new String[5];
		animate[0] = "MoVe Dorothy 100 100";
		animate[1] = "saY DOROTHY \"hi5!\"";
		animate[2] = "MoVe WIZARD -50 -50";
		animate[3] = "say scarecrow \"I'm scary!\"";
		animate[4] = "move ScareCrow 10 10";
		for (int i = 0; i < animate.length; i++) {
			parser.setString(animate[i]);
			ThreadSupport.sleep(3000);
		}
	}
	
	public void init() {
		canvas.reference(OE);
		scanner.reference(OE);
		//parser.reference(OE);
		canvas.init();
		//canvas.animate();
		//animateScene();
		canvas.getDorothyAvatar().rotateLeftArm(1);
		canvas.getDorothyAvatar().rotateRightArm(1);
		canvas.getDorothyAvatar().rotateLeftLeg(1);
		canvas.getDorothyAvatar().rotateRightLeg(1);
	}
	
	public void reference(OEFrame editor) {
		OE = editor;
	}
}