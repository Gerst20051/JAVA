package a10_commands;

import assignment10.Avatar;
import assignment10.Canvas;

public class ACommandObjectSay implements Runnable {
	Avatar avatar;
	String string;
	Canvas canvas;
	
	public ACommandObjectSay(Avatar initAvatar, String initString, Canvas initCanvas) {
		avatar = initAvatar;
		string = initString;
		canvas = initCanvas;
	}
	
	public void run() {
		avatar.say(string);
	}
}