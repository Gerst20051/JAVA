package commandpackage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.Arrays;
import java.util.Scanner;

import objectpackage.ABackground;
import objectpackage.ARotatingLine;
import objectpackage.AnImage;
import objectpackage.ImageInterface;

import main.AAvatarAnimationCommand;
import main.AAvatarAnimator;
import main.AAvatarWaitingAnimator;
import main.ABroadcastingClearanceManager;
import main.ADorothyWaiting;
import main.AOzWaiting;
import main.APropertyListenerSupport;
import main.AScarecrowWaiting;
import main.BroadcastingClearanceManager;
import main.PropertyListenerSupport;
import compositepackage.*;
import scannerpackage.*;
import table.ATable;
import table.TableInterface;
import tokenpackage.*;
import tokenpackage.Number;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
import util.misc.Console;

@StructurePattern("Bean Pattern")
public class ACommandInterpreter implements CommandInterpreter {
	int x, y;
	int searchindex = 0;
	OzScene ozscene;
	AScanner scanner;
	String command, dorothy, scarecrow, oz;
	TableInterface table = new ATable();
	ACommandObjectMove acommandobjectmove;
	ACommandObjectSay acommandobjectsay;
	ARepeatCompositeCommand arepeatcompositecommand;
	StartToken starttoken;
	Token[] myArray;
	BroadcastingClearanceManager broadcastingclearancemanager;
	AAvatarAnimator avataranimator;
	AAvatarWaitingAnimator avatarwaitinganimator;
	AOzWaiting aozwaiting;
	AScarecrowWaiting ascarecrowwaiting;
	ADorothyWaiting adorothywaiting;
	
	public ACommandInterpreter(OzScene initozscene, AScanner initscanner,
			BroadcastingClearanceManager initbroadcastingclearancemanager) {
		ozscene = initozscene;
		scanner = initscanner;
		setAvatar();
		broadcastingclearancemanager = initbroadcastingclearancemanager;
		avataranimator = new AAvatarAnimator();
		avatarwaitinganimator = new AAvatarWaitingAnimator(broadcastingclearancemanager);
		aozwaiting = new AOzWaiting(broadcastingclearancemanager);
		ascarecrowwaiting = new AScarecrowWaiting(broadcastingclearancemanager);
		adorothywaiting = new ADorothyWaiting(broadcastingclearancemanager);
		
	}
	
	public void setAvatar() {
		table.put("dorothy", ozscene.getDorothy());
		table.put("scarecrow", ozscene.getScarecrow());
		table.put("oz", ozscene.getOz());
	}

//	public Token[] getTokens() {
//		return initArray;
//	}

	public void setcommand(String newcommand) {
		searchindex = 0;
		command = newcommand;
		scanner.setString(command);
		myArray = scanner.getArray();
		Runnable a = parseCommand();
		a.run();
	}

	public Command parseCommand() {
		if (myArray[searchindex] instanceof say) {
			return parseSay();
		} else if (myArray[searchindex] instanceof move) {
			return parseMove();
		} else if (myArray[searchindex] instanceof StartToken) {
			return parseCommandList();
		} else if (myArray[searchindex] instanceof repeat) {
			return parseRepeatCommand();
		}
		else {
			return null; 
		}
	}

	public Command parseSay() {
		searchindex++;
		Avatar avatar = (Avatar) table.get(myArray[searchindex].getTokenString()
				.toLowerCase());
		searchindex++;
		return new ACommandObjectSay(avatar, myArray[searchindex].getTokenString(),
				ozscene);
	}

	public Command parseMove() {
		searchindex++;
		AnAvatar avatar = (AnAvatar) table.get(myArray[searchindex].getTokenString()
				.toLowerCase());
		searchindex++;
		x = Integer.parseInt(myArray[searchindex].getTokenString());
		searchindex++;
		y = Integer.parseInt(myArray[searchindex].getTokenString());
		return new ACommandObjectMove(avatar, x, y, ozscene);
	}

	public Command parseRepeatCommand() {
		searchindex++;
		int multiplier = Integer.parseInt(myArray[searchindex].getTokenString());
		searchindex++;
		return new ARepeatCompositeCommand(multiplier, parseCommand());
	}

	//Line1: Get the current location of x and y 
	//Line2: use your move method to move to the nex location x1 = x + 10, x2 = y + 10
	//Line3: 

	public Command parseCommandList() {
		ACommandListCompositeCommand acommandlistcompositecommand = new ACommandListCompositeCommand();	
		searchindex++;
		while (searchindex < myArray.length) {
			if (myArray[searchindex] instanceof EndToken) {
				System.out.println("Did it ever reach to this point?");
				break;
			} else {
				acommandlistcompositecommand.addElement(parseCommand());
				searchindex++;
			}
		}
		return acommandlistcompositecommand;
	}

	public void animateDorothy() {
		System.out.println("Is it working?");
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator,
				ozscene.getDorothy(), 50, 10));
		thread.start();
	}

	public void animateOz() {
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator,
				ozscene.getOz(), 50, 10));
		thread.start();
	}

	public void animateScarecrow() {
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator,
				ozscene.getScarecrow(), 50, 10));
		thread.start();
	}

	public void waitinganimateDoroty() {
//		broadcastingclearancemanager.waitForProceed();
		System.out.println("Is it waiting for Dorothy?");
		Thread thread = new Thread(new AAvatarAnimationCommand(adorothywaiting,
				ozscene.getDorothy(), 50, 10));
		thread.start();
	}

	public void waitinganimateOz() {
//		broadcastingclearancemanager.waitForProceed();
		System.out.println("Is it waiting for Oz?");
		Thread thread = new Thread(new AAvatarAnimationCommand(aozwaiting,
				ozscene.getOz(), 50, 10));
		thread.start();
	}

	public void waitinganimateScarecrow() {
//		broadcastingclearancemanager.waitForProceed();
		System.out.println("Is it waiting for Scarecrow?");
		Thread thread = new Thread(new AAvatarAnimationCommand(ascarecrowwaiting,
				ozscene.getScarecrow(), 50, 10));
		thread.start();
	}

	public void animatestart() {
		broadcastingclearancemanager.proceedAll();

	}
	
//	public void move() {
//		
//	}
}
