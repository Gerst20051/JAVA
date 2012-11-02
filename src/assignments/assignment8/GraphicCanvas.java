package assignment8;

public interface GraphicCanvas {
	public CanvasImage getBackgroundImage();
	public Avatar getDorothyAvatar();
	public Avatar getScarecrowAvatar();
	public Avatar getWizardAvatar();
	public void animate();
	public void init();
}