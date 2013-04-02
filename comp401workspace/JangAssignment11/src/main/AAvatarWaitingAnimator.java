package main;

import compositepackage.Avatar;

public class AAvatarWaitingAnimator extends AAvatarAnimator{
	BroadcastingClearanceManager broadcastingclearancemanager = new ABroadcastingClearanceManager();

	public AAvatarWaitingAnimator(BroadcastingClearanceManager abroadcastingclearancemanager) {
		broadcastingclearancemanager = abroadcastingclearancemanager;
	}
	public synchronized void animate(Avatar avatar, int animationStep, int animationPauseTime) { 
		if(broadcastingclearancemanager == null)System.out.println("Null manager");
        broadcastingclearancemanager.waitForProceed();
        super.animate(avatar, animationStep, animationPauseTime);
    }
}
