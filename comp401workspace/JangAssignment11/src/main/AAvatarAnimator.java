package main;

import bus.uigen.OEFrame;
import util.misc.ThreadSupport;
import compositepackage.Avatar;
import compositepackage.OzScene;

public class AAvatarAnimator {
	Avatar avatar;
	OzScene ozscene;

	public AAvatarAnimator() {
		///avatar = initAvatar;
		//ozscene = initOzScene;
	}

	public void animate(Avatar avatar, int animationStep,
			int animationPauseTime) {
		int orignialX = avatar.getX();
		int originalY = avatar.getY();
		int curX = 0;
		int curY = 0;
		avatar.setX(curX);
		avatar.setY(curY);
		while (curY < originalY) 
		{
			ThreadSupport.sleep(animationPauseTime);
			curY += animationStep;
			avatar.setY(curY);
			//frame.refresh();
		}
		avatar.setY(originalY);
		while (curX < orignialX) {
			ThreadSupport.sleep(animationPauseTime);
			curX += animationStep;
			avatar.setX(curX);
			//frame.refresh();
		}
		avatar.setX(orignialX);
		
	}
}
