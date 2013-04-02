package commandpackage;

import tokenpackage.Token;
import tokenpackage.Number;
import tokenpackage.Word;

import compositepackage.AnAvatar;
import compositepackage.Avatar;
import compositepackage.OzScene;

public class ACommandObjectMove implements Command {

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
		avatar.move(x, y);
//		avatar.setX(x);
//		avatar.setY(y);
		avatar.getHead();
		avatar.getTorso();
		avatar.getArms();
		avatar.getLegs();		
	}

}
