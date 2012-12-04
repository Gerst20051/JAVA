package assignment10;

public interface Scene {
	public Canvas getCanvas();
	public AScanner getScanner();
	public AParser getParser();
	public ATable getTable();
	public void init();
}