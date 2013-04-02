package compositepackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
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
import util.annotations.StructurePattern;
import util.models.PropertyListenerRegisterer;

@StructurePattern("Bean Pattern")
public class AOzScene implements OzScene, PropertyListenerRegisterer{

	Avatar dorothy, scarecrow, oz, adorothy, ascarecrow, aoz;
	ImageInterface background;
	int x_target, y_target;
	PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	
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
		setTable();
	}

//How to implement this part of extra credits? Observable 
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
	}

	@Position(4)
	public ImageInterface getBackground(){
		assert preGetBackground();
//		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", getBackground(), getBackground()));
		return background;
	}
	@Position(1)
	public Avatar getDorothy(){
		assert preGetDorothy();
//		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", getDorothy(), getDorothy()));
		return dorothy;
	}
	@Position(2)
	public Avatar getScarecrow(){
		assert preGetScarecrow();
//		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", getScarecrow(), getScarecrow()));
		return scarecrow;
	}
	@Position(3)
	public Avatar getOz(){
		assert preGetOz();
//		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", getOz(), getOz()));
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
		this.getDorothy().setX(0);
		this.getDorothy().setY(100);
		this.getOz().setX(100);
		this.getOz().setY(100);
		this.getScarecrow().setX(200);
		this.getScarecrow().setY(100);
		this.getDorothy();
		this.getOz();
		this.getScarecrow();
	}


	//Assignment10
	
		TableInterface table = new ATable();
		public void setTable(){
			table.put("background", true);
			table.put("dorothy", true);
			table.put("scarecrow", true);
			table.put("oz", true);
		}
	
		public boolean preGetDorothy() {
			return (Boolean) table.get("dorothy");
		}
		
		public boolean preGetOz() {
			return (Boolean) table.get("oz");
		}
		
		public boolean preGetScarecrow() {
			return (Boolean) table.get("scarecrow");
		}
		
		public boolean preGetBackground() {
			return (Boolean) table.get("background");
		}
		
		public void dorothyEnters() {
			assert preDorothyEnters();
			table.put("dorothy",true);
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Dorothy", true));
		}
		
		public void dorothyLeaves() {
			assert preDorothyLeaves();
			table.put("dorothy", false);
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Dorothy", false));			
		}
		
		public void ozEnters() {
			assert preOzEnters();
			table.put("oz", true);
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Oz", true));			
		}
		
		public void ozLeaves() {
			assert preOzLeaves();
			table.put("oz", false);
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Oz", false));			

		}
		
		public void scarecrowEnters() {
			assert preScarecrowEnters();
			table.put("scarecrow", true);
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Scarecrow", true));
		}
		
		public void scarecrowLeaves() {
			assert preScarecrowLeaves();
			table.put("scarecrow", false);
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Scarecrow", false));
		}
		
		public void backgroundEnters() {
			assert preBackgroundEnters();
			table.put("background",true);
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Background", true));
		}
		
		public void backgroundLeaves() {
			assert preBackgroundLeaves();
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Background", false));
			table.put("background", false);
		}
		//Extra Credit
		public boolean preDorothyEnters() {
			return (Boolean) table.get("dorothy");
		}
		
		public boolean preOzEnters() {
			return (Boolean) table.get("oz");
		}
		
		public boolean preScarecrowEnters() {
			return (Boolean) table.get("scarecrow");
		}
		
		public boolean preBackgroundEnters() {
			return (Boolean) table.get("background");
		}
		//Extra Credit
		public boolean preDorothyLeaves() {
			return (Boolean) table.get("dorothy");
		}
		
		public boolean preOzLeaves() {
			return (Boolean) table.get("oz");
		}
		
		public boolean preScarecrowLeaves() {
			return (Boolean) table.get("scarecrow");
		}
		
		public boolean preBackgroundLeaves() {
			return (Boolean) table.get("background");
		}
}
