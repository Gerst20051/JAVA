package compositepackage;

import java.beans.PropertyChangeListener;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import objectpackage.ImageInterface;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
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
	PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
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
