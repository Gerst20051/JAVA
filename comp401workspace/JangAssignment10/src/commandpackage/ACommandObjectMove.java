package commandpackage;

import tokenpackage.GeneralTokenInterface;
import tokenpackage.Number;
import tokenpackage.Word;

import compositepackage.AnAvatar;
import compositepackage.Avatar;
import compositepackage.OzScene;

public class ACommandObjectMove implements Runnable {

	int x,y;
	Avatar avatar; 
	OzScene scene;
	
	public ACommandObjectMove(Avatar initAvatar, int initX, int initY, OzScene initScene) {
		avatar = initAvatar;
		x = initX;
		y = initY;
		scene = initScene;
	}

	public void run() {
		avatar.setX(x);
		avatar.setY(y);
		avatar.getHead();
		avatar.getTorso();
		avatar.getArms();
		avatar.getLegs();		
	}

}
