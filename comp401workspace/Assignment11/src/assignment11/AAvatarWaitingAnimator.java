package assignment11;

public class AAvatarWaitingAnimator extends AAvatarAnimator {
	BroadcastingClearanceManager broadcastingclearancemanager = new ABroadcastingClearanceManager();

	public AAvatarWaitingAnimator(BroadcastingClearanceManager abroadcastingclearancemanager) {
		broadcastingclearancemanager = abroadcastingclearancemanager;
	}
	
	public synchronized void animate(Avatar avatar, int step, int delay) {
        broadcastingclearancemanager.waitForProceed();
        super.animate(avatar, step, delay);
    }
}