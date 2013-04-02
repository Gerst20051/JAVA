package assignment7;

import bus.uigen.OEFrame;

public interface GraphicCanvas {
	public CanvasImage getBackgroundImage();
	public Avatar getDorothyAvatar();
	public Avatar getScarecrowAvatar();
	public Avatar getWizardAvatar();
	public void animate();
	public void init();
	public void reference(OEFrame object);
}