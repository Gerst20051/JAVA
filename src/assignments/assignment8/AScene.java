package assignment8;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import util.misc.ThreadSupport;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class AScene implements Scene {
	OEFrame OE;
	Canvas canvas = new Canvas();
	AScanner scanner = new AScanner("");
	AParser parser = new AParser(canvas, scanner);
	ATable table = new ATable();
	OEFrame OEscanner = ObjectEditor.edit(scanner);
	OEFrame OEparser = ObjectEditor.edit(parser);
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
        listener.propertyChange(new PropertyChangeEvent(this, "canvas", 0, getCanvas()));
	}
	
	public AScene() {
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public AScanner getScanner() {
		return scanner;
	}
	
	public AParser getParser() {
		return parser;
	}
	
	public ATable getTable() {
		return table;
	}
	
	private void animateScene() {
		String[] animate = new String[10];
		animate[0] = "saY DOROTHY \"hi5!\"";
		animate[1] = "MoVe Dorothy 100 100";
		animate[2] = "MoVe WIZARD -50 -50";
		animate[3] = "say scarecrow \"I'm scary!\"";
		animate[4] = "move dorothy -100 -100";
		animate[5] = "hello WIZARD -50 -50"; // [0] not command
		animate[6] = "MoVe 200 100 100"; // [1] not word
		animate[7] = "saY DOROTHY 100"; // [2] not quote
		animate[8] = "move ScareCrow \"hey\" 10"; // [2] not number (is quote)
		animate[9] = "move SCARECROW 250 -"; // [3] not number (is sign)
		for (int i = 0; i < animate.length; i++) {
			Canvas oldCanvas = getCanvas();
			parser.setString(animate[i]);
			listeners.notifyAllListeners(new PropertyChangeEvent(this, "canvas", oldCanvas, getCanvas()));
			ThreadSupport.sleep(1000);
			//OEscanner.refresh();
			//OEparser.refresh();
		}
	}
	
	@SuppressWarnings("unused")
	private void reverseScene() {
		for (int i = 0; i < parser.getHistory().size(); i++) {
			// Starting to work on undo functions
		}
	}
	
	public void init() {
		//canvas.reference(OE);
		//scanner.reference(OE);
		//parser.reference(OE);
		//canvas.listeners(listeners);
		canvas.init();
		//animateScene();
	}
	
	public void reference(OEFrame editor) {
		OE = editor;
	}
}