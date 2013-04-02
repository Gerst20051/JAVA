package commandpackage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

import objectpackage.ABackground;
import objectpackage.ARotatingLine;
import objectpackage.AnImage;
import objectpackage.ImageInterface;

import main.AAvatarAnimationCommand;
import main.AAvatarAnimator;
import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import compositepackage.*;
import scannerpackage.*;
import table.ATable;
import table.TableInterface;
import tokenpackage.*;
import tokenpackage.Number;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;

public class ACommandInterpreter implements CommandInterpreter {
	int x, y;
	OzScene ozscene;
	AScanner scanner;
	String command, dorothy, scarecrow, oz;
	GeneralTokenInterface[] tokenarray;
	TableInterface table = new ATable();
	ACommandObjectMove acommandobjectmove;
	ACommandObjectSay acommandobjectsay;
	AAvatarAnimator avataranimator= new AAvatarAnimator();

	public ACommandInterpreter(OzScene initozscene, AScanner initscanner) {
		ozscene = initozscene;
		scanner = initscanner;
		setAvatar();
	}

	public void setAvatar() {
		table.put("dorothy", ozscene.getDorothy());
		table.put("scarecrow", ozscene.getScarecrow());
		table.put("oz", ozscene.getOz());
	}

	public GeneralTokenInterface[] getTokens() {
		return tokenarray;
	}

	public void setcommand(String newcommand) {
		command = newcommand;
		scanner.setString(command);
		tokenarray = scanner.getArray();
		initialParse(tokenarray);
		System.out.println(getTokens()[0].getClass());
		System.out.println(getTokens()[1].getClass());
		System.out.println(getTokens()[2].getClass());
	}

	public void initialParse(GeneralTokenInterface[] initArray) {
		if (initArray[0] instanceof say) {
			ACommandObjectSay say1 =parseSay(initArray);
			say1.run();
		} else if (initArray[0] instanceof move) {
			ACommandObjectMove move1 = parseMove(initArray);
			move1.run();
		}

	}

	public ACommandObjectSay parseSay(GeneralTokenInterface[] initArray) {
		Avatar avatar = (Avatar) table.get(initArray[1].getTokenString()
				.toLowerCase());
		return new ACommandObjectSay(avatar, initArray[2].getTokenString(), ozscene);

	}

	public ACommandObjectMove parseMove(GeneralTokenInterface[] initArray) {
		AnAvatar avatar = (AnAvatar) table.get(initArray[1].getTokenString()
				.toLowerCase());
		if (initArray[2] instanceof Number) {
			x = Integer.parseInt(tokenarray[2].getTokenString());
			if (initArray[3] instanceof Number) {
				y = Integer.parseInt(tokenarray[3].getTokenString());
			}
		}
		return new ACommandObjectMove(avatar, x, y, ozscene);
	}

	
	public void animateDorothy() {
		System.out.println("Is it working?");
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator,ozscene.getDorothy(),50,10));
		thread.start();
	}
	
	public void animateOz() {
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator,ozscene.getOz(),50,10));
		thread.start();
	}
	
	public void animateScarecrow() {
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator,ozscene.getScarecrow(),50,10));
		thread.start();
	}
}

