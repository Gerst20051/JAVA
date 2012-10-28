package assignment8;

import bus.uigen.OEFrame;

public interface Scene {
	public Canvas getCanvas();
	public AScanner getScanner();
	public AParser getParser();
	public ATable getTable();
	public void init();
	public void reference(OEFrame editor);
}