package assignment11;

import bus.uigen.OEFrame;

public class AAvatarAnimationCommand implements Runnable {
	Avatar avatar;
	AAvatarAnimator avataranimator;
	int step;
	int delay;
	OEFrame frame;
	
	public AAvatarAnimationCommand(AAvatarAnimator initAnimator, Avatar initAvatar, int initStep, int initDelay) {
		avataranimator = initAnimator;
		avatar = initAvatar;
		step = initStep;
		delay = initDelay;
	}
	
	public void run() {
		avataranimator.animate(avatar, step, delay);
	}
}