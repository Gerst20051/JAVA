package assignment10;

import util.misc.ThreadSupport;

public class AAvatarAnimator {
	public AAvatarAnimator() {}

	public void animate(Avatar avatar, int step, int delay) {
		int orignialX = avatar.getX();
		int originalY = avatar.getY();
		int curX = 0;
		int curY = 0;
		avatar.setLocation(curX, curY);
		while (curX < orignialX) {
			ThreadSupport.sleep(delay);
			curX += step;
			avatar.setX(curX);
		}
		avatar.setX(orignialX);
		while (curY < originalY) {
			ThreadSupport.sleep(delay);
			curY += step;
			avatar.setY(curY);
		}
		avatar.setY(originalY);
	}
}