package objectpackage;

public class ABackground extends AnImage implements ImageInterface{
	String imageFileName;
	int x,y;
	public ABackground (String initImageFileName, int iniX, int iniY){
		super(initImageFileName, iniY, iniY);
	}
	
    public static final String SHUTTLE_IMAGE_FILE_NAME = "Background.jpg";
 
}
