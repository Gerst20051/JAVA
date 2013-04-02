package compositepackage;

import objectpackage.ImageInterface;
import util.annotations.Position;

public class AOzScene implements OzScene{
	ImageInterface aBackground;
	Avatar aDorothy, aScarecrow, aOz; 
	
	public AOzScene(ImageInterface initBackground, Avatar initDorothy, Avatar initScarecrow, Avatar initOz) {
		aBackground = initBackground;
		aDorothy = initDorothy;
		aScarecrow = initScarecrow;
		aOz = initOz;
	}
	
	@Position(4)
	public ImageInterface getBackground(){
		return aBackground;
	}
	@Position(1)
	public Avatar getDorothy(){
		return aDorothy;
	}
	@Position(2)
	public Avatar getScarecrow(){
		return aScarecrow;
	}
	@Position(3)
	public Avatar getOz(){
		return aOz; 
	}

}
