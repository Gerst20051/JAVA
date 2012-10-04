package assignment6;

import bus.uigen.OEFrame;

public interface GraphicCanvas {
	public CanvasImage getBackgroundImage();
	public Avatar getDorothyAvatar();
	public Avatar getScarecrowAvatar();
	public Avatar getWizardAvatar();
	public void init();
	public void reference(OEFrame object);
}