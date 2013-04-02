package a11_commands;

import assignment11.Avatar;
import assignment11.Canvas;

public class ACommandObjectSay implements Command {
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