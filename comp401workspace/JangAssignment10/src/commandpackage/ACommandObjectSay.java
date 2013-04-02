package commandpackage;

import objectpackage.StringShape;
import tokenpackage.QuotedString;
import tokenpackage.Word;

import compositepackage.Avatar;
import compositepackage.OzScene;

public class ACommandObjectSay implements Runnable{

	Avatar avatar;
	String string;
	OzScene scene;
	//Animator animator; 
	
	public ACommandObjectSay(Avatar initAvatar, String initString, OzScene initScene) {
		avatar = initAvatar;
		string = initString;
		scene = initScene;
		//animator = initAnimator; 
	}
	
	public void run() {
		avatar.setText(string);
		//animator.animate(avatar);
	}
		
	
}
