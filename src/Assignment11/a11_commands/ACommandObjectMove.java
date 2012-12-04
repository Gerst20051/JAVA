package a11_commands;

import assignment11.Avatar;
import assignment11.Canvas;

public class ACommandObjectMove implements Runnable {
	int x, y;
	Avatar avatar;
	Canvas canvas;
	
	public ACommandObjectMove(Avatar initAvatar, int initX, int initY, Canvas initCanvas) {
		avatar = initAvatar;
		x = initX;
		y = initY;
		canvas = initCanvas;
	}

	public void run() {
		avatar.moveLocation(x,y);
	}
}