package assignment11;

import bus.uigen.OEFrame;
import a11_commands.*;
import a11_token.*;
import a11_token.Number;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.Thread;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class AParser implements Parser {
	int x, y;
	int searchindex = 0;
	String command, dorothy, scarecrow, oz;
	Start starttoken;
	OEFrame OE;
	Canvas canvas;
	AScanner scanner;
	Token[] tokens;
	ATable avatars = new ATable();
	AObjectDatabase history = new AObjectDatabase();
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	//AAvatarAnimator avataranimator = new AAvatarAnimator();
	AAvatarAnimator avataranimator;
	ACommandObjectMove acommandobjectmove;
	ACommandObjectSay acommandobjectsay;
	ARepeatCompositeCommand arepeatcompositecommand;
	BroadcastingClearanceManager broadcastingclearancemanager;
	AAvatarWaitingAnimator avatarwaitinganimator;
	ADorothyWaiting adorothywaiting;
	AScarecrowWaiting ascarecrowwaiting;
	AWizardWaiting awizardwaiting;
	
	public AParser(Canvas canvas, AScanner scanner) {
		this.canvas = canvas;
		this.scanner = scanner;
		setAvatars();
	}
	
	public AParser(Canvas canvas, AScanner scanner, BroadcastingClearanceManager initbroadcastingclearancemanager) {
		this.canvas = canvas;
		this.scanner = scanner;
		setAvatars();
		broadcastingclearancemanager = initbroadcastingclearancemanager;
		avataranimator = new AAvatarAnimator();
		avatarwaitinganimator = new AAvatarWaitingAnimator(broadcastingclearancemanager);
		adorothywaiting = new ADorothyWaiting(broadcastingclearancemanager);
		ascarecrowwaiting = new AScarecrowWaiting(broadcastingclearancemanager);
		awizardwaiting = new AWizardWaiting(broadcastingclearancemanager);
	}
	
	public Token[] getTokens() {
		return tokens;
	}
	
	public AObjectDatabase getHistory() {
		return history;
	}
	
	private void setAvatars() {
		avatars.put("dorothy", canvas.getDorothyAvatar());
		avatars.put("scarecrow", canvas.getScarecrowAvatar());
		avatars.put("wizard", canvas.getWizardAvatar());
	}
	
	public Command interpretCommand() {
		if (getTokens()[searchindex] instanceof Move) {
			return parseMove();
		} else if (getTokens()[searchindex] instanceof Say) {
			return parseSay();
		} else if (getTokens()[searchindex] instanceof Start) {
			System.out.println("STARTTTTTT");
			return parseCommandList();
		} else if (getTokens()[searchindex] instanceof Repeat) {
			System.out.println("REPEATTTTT");
			return parseRepeatCommand();
		} else {
			return null;
		}
	}

	public Command parseMove() {
		int x = 0, y = 0;
		Avatar avatar = null;
		if (getTokens()[1] instanceof Word) {
			avatar = (Avatar) avatars.get(getTokens()[1].getToken().toLowerCase());
			if (4 < getTokens().length-1) {
				if (!(getTokens()[2] instanceof Number)) {
					if ((getTokens()[4] instanceof Number)) {
						x = ((Number) getTokens()[3]).getNumber();
						if (getTokens()[2] instanceof Minus) x = -x;
						y = ((Number) getTokens()[4]).getNumber();
					} else {
						x = ((Number) getTokens()[3]).getNumber();
						if (getTokens()[2] instanceof Minus) x = -x;
						y = ((Number) getTokens()[5]).getNumber();
						if (getTokens()[4] instanceof Minus) y = -y;
					}
				} else if (!(getTokens()[3] instanceof Number)) {
					x = ((Number) getTokens()[2]).getNumber();
					y = ((Number) getTokens()[4]).getNumber();
					if (getTokens()[3] instanceof Minus) y = -y;
				}
			} else {
				if (getTokens()[2] instanceof Number && getTokens()[3] instanceof Number) {
					x = ((Number) getTokens()[2]).getNumber();
					y = ((Number) getTokens()[3]).getNumber();
				} else {
					if (!(getTokens()[2] instanceof Number) && !(getTokens()[3] instanceof Number)) {
						System.out.println("ERROR! 3rd and 4th arguments in command MOVE should be integers. You provided type "+getTokens()[2].getClass().getName().substring(9)+" and "+getTokens()[3].getClass().getName().substring(9)+".");
					} else if (!(getTokens()[2] instanceof Number)) {
						System.out.println("ERROR! 3rd argument in command MOVE should be an integer. You provided type "+getTokens()[2].getClass().getName().substring(9)+".");
					} else if (!(getTokens()[3] instanceof Number)) {
						System.out.println("ERROR! 4th argument in command MOVE should be an integer. You provided type "+getTokens()[3].getClass().getName().substring(9)+".");
					}
				}
			}
			listeners.notifyAllListeners(new PropertyChangeEvent(this, "tokens", getTokens(), getTokens()));
		} else {
			System.out.println("ERROR! 2nd argument in command MOVE should be a Word. You provided type "+getTokens()[1].getClass().getName().substring(10)+".");
		}
		return new ACommandObjectMove(avatar, x, y, canvas);
	}
	
	public Command parseSay() {
		Avatar avatar = null;
		if (getTokens()[1] instanceof Word) {
			if (getTokens()[2] instanceof Quote) {
				avatar = (Avatar) avatars.get(getTokens()[++searchindex].getToken().toLowerCase());
			} else {
				System.out.println("ERROR! 3rd argument in command SAY should be a Quote. You provided type "+getTokens()[2].getClass().getName().substring(9)+".");
			}
		} else {
			System.out.println("ERROR! 2nd argument in command SAY should be a Word. You provided type "+getTokens()[1].getClass().getName().substring(9)+".");
		}
		return new ACommandObjectSay(avatar, getTokens()[++searchindex].getToken(), canvas);
	}
	
	public Command parseCommandList() {
		ACommandListCompositeCommand acommandlistcompositecommand = new ACommandListCompositeCommand();	
		searchindex++;
		while (searchindex < getTokens().length) {
			if (getTokens()[searchindex] instanceof End) {
				break;
			} else {
				acommandlistcompositecommand.addElement(interpretCommand());
				searchindex++;
			}
		}
		return acommandlistcompositecommand;
	}
	
	public Command parseRepeatCommand() {
		int reps = Integer.parseInt(getTokens()[++searchindex].getToken());
		searchindex++;
		return new ARepeatCompositeCommand(reps, interpretCommand());
	}
	
	public void animateDorothy() {
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator, canvas.getDorothyAvatar(), 10, 10));
		thread.start();
	}
	
	public void animateScarecrow() {
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator, canvas.getScarecrowAvatar(), 10, 10));
		thread.start();
	}
	
	public void animateWizard() {
		Thread thread = new Thread(new AAvatarAnimationCommand(avataranimator, canvas.getWizardAvatar(), 10, 10));
		thread.start();
	}
	
	public void waitingAnimateDorothy() {
		Thread thread = new Thread(new AAvatarAnimationCommand(adorothywaiting, canvas.getDorothyAvatar(), 10, 10));
		thread.start();
	}
	
	public void waitingAnimateScarecrow() {
		Thread thread = new Thread(new AAvatarAnimationCommand(ascarecrowwaiting, canvas.getScarecrowAvatar(), 10, 10));
		thread.start();
	}
	
	public void waitingAnimateWizard() {
		Thread thread = new Thread(new AAvatarAnimationCommand(awizardwaiting, canvas.getWizardAvatar(), 10, 10));
		thread.start();
	}
	
	public void animateStart() {
		broadcastingclearancemanager.proceedAll();
	}
	
	public void setString(String input) {
		searchindex = 0;
		scanner.setString(input);
		tokens = scanner.getTokens();
		history.addElement(tokens);
		interpretCommand();
		Runnable a = interpretCommand();
		a.run();
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "history", getHistory(), getHistory()));
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "tokens", getTokens(), getTokens()));
	}
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
		listener.propertyChange(new PropertyChangeEvent(this, "history", 0, getHistory()));
        listener.propertyChange(new PropertyChangeEvent(this, "tokens", 0, getTokens()));
	}
}