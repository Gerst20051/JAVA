package compositepackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import objectpackage.ABackground;
import objectpackage.ARotatingLine;
import objectpackage.AnImage;
import objectpackage.ImageInterface;
import table.ATable;
import table.TableInterface;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.Position;
import util.models.PropertyListenerRegisterer;

public class AOzScene implements OzScene, PropertyListenerRegisterer{

	Avatar dorothy, scarecrow, oz, adorothy, ascarecrow, aoz;
	ImageInterface background;
	int x_target, y_target;
	
//model	
	public AOzScene() {

		int x1 = 0;
		int x2 = 100;
		int x3 = 200;
		int y = 100;

		background = new ABackground("Background.jpg", 0, 0);
		dorothy = new AnAvatar(x1, y,
				new AnImage("Dorothy.jpg", x1, y), new ARotatingLine(x1 + 40,
						y + 80, 40, Math.PI / 2), new AnAngle(x1 + 40, y + 80,
						new ARotatingLine(x1, y, 50, Math.PI / 4),
						new ARotatingLine(x1, y, 50, (Math.PI / 4)
								+ (Math.PI / 2))), new AnAngle(x1 + 40,
						x1 + 220, new ARotatingLine(x1, y, 50, Math.PI / 4),
						new ARotatingLine(x1, y, 50, (Math.PI / 4)
								+ (Math.PI / 2))));
		scarecrow = new AnAvatar(x2, y, new AnImage("ScareCrow.jpg",
				x2, y), new ARotatingLine(x2 + 40, y + 80, 40, Math.PI / 2),
				new AnAngle(x2 + 40, y + 80, new ARotatingLine(x2, y, 50,
						Math.PI / 4), new ARotatingLine(x2, y, 50,
						(Math.PI / 4) + (Math.PI / 2))), new AnAngle(x2 + 40,
						x2 + 120, new ARotatingLine(x2, y, 50, Math.PI / 4),
						new ARotatingLine(x2, y, 50, (Math.PI / 4)
								+ (Math.PI / 2))));
		oz = new AnAvatar(x3, y, new AnImage("OZ.jpg", x3, y),
				new ARotatingLine(x3 + 40, y + 80, 40, Math.PI / 2),
				new AnAngle(x3 + 40, y + 80, new ARotatingLine(x3, y, 50,
						Math.PI / 4), new ARotatingLine(x3, y, 50,
						(Math.PI / 4) + (Math.PI / 2))), new AnAngle(x3 + 40,
						x3 + 20, new ARotatingLine(x3, y, 50, Math.PI / 4),
						new ARotatingLine(x3, y, 50, (Math.PI / 4)
								+ (Math.PI / 2))));
	}

	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.getDorothy().addPropertyChangeListener(listener);
		this.getBackground().addPropertyChangeListener(listener);
		this.getOz().addPropertyChangeListener(listener);
		this.getScarecrow().addPropertyChangeListener(listener);
	}

	@Position(4)
	public ImageInterface getBackground(){
		return background;
	}
	@Position(1)
	public Avatar getDorothy(){
//		assert preGetDorothy();
		return dorothy;
	}
	@Position(2)
	public Avatar getScarecrow(){
//		assert preGetScarecrow();
		return scarecrow;
	}
	@Position(3)
	public Avatar getOz(){
//		assert preGetOz();
		return oz; 
	}
	

	@Override
	public void setX(int initX) {
		x_target = initX;
		
	}
	
	@Override
	public void setY(int initY) {
		y_target = initY;
		
	}
	
	public void setDorothyXY(){
		this.getDorothy().setX(x_target);
		this.getDorothy().setY(y_target);
		this.getDorothy();
	}
	
	public void setScarecrowXY(){
		this.getScarecrow().setX(x_target);
		this.getScarecrow().setY(y_target);
		this.getScarecrow();
	}
	
	public void setOZXY(){
		this.getOz().setX(x_target);
		this.getOz().setY(y_target);
		this.getOz();
	}
	
	public void setOriginalXY(){
			
	}

}
