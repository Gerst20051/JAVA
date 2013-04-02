package main;

import bus.uigen.OEFrame;
import compositepackage.Avatar;

public class AAvatarAnimationCommand implements Runnable {
	Avatar avatar;
	AAvatarAnimator avataranimator;
	int animationStep;
	int animationPauseTime;
	OEFrame frame; 
	
	public AAvatarAnimationCommand(AAvatarAnimator aavataranimator, Avatar aavatar, int ananimationStep, int ananimationpausetime){
		avatar = aavatar;
		avataranimator = aavataranimator;
		animationStep = ananimationStep;
		animationPauseTime = ananimationpausetime;
		//OEFrame frame; 
	}
	
	public void run() {
		avataranimator.animate(avatar, animationStep, animationPauseTime); 
	}
}
