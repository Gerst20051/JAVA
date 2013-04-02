package assignment11;

import java.awt.Component;
import javax.swing.JFrame;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Main {
	public static void main (String[] args) {
		Canvas canvas = new Canvas();
		AScanner scanner = new AScanner("");
		TextComponentInterface jtextfield = new AJTextField();
		BroadcastingClearanceManager broadcastingclearancemanager = new ABroadcastingClearanceManager();
		AParser parser = new AParser(canvas, scanner, broadcastingclearancemanager);
		
		canvas.init();
		
		OEFrame oeFrame1 = ObjectEditor.edit(canvas);
		oeFrame1.hideMainPanel();
		oeFrame1.setLocation(0, 0);
		oeFrame1.setSize(650, 695);
		
		JFrame frame = new JFrame("Command Input");
		frame.add((Component) jtextfield);
		frame.setSize(250, 150);
		frame.setVisible(true);
		
		AController controller = new AController(parser, jtextfield);
		controller.setModel(parser);
		
		ObjectEditor.edit(parser);
		parser.waitingAnimateDorothy();
		parser.waitingAnimateScarecrow();
		parser.waitingAnimateWizard();
		parser.animateStart();
	}
}