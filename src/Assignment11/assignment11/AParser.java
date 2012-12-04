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
	OEFrame OE;
	Canvas canvas;
	AScanner scanner;
	Token[] tokens;
	ATable avatars = new ATable();
	AObjectDatabase history = new AObjectDatabase();
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	AAvatarAnimator avataranimator = new AAvatarAnimator();
	ACommandObjectMove acommandobjectmove;
	ACommandObjectSay acommandobjectsay;
	
	public AParser(Canvas canvas, AScanner scanner) {
		this.canvas = canvas;
		this.scanner = scanner;
		setAvatars();
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
	
	public void interpretCommand() {
		if (!(getTokens()[0] instanceof Command)) {
			System.out.println("ERROR! 1st argument in string you provided is of type "+getTokens()[0].getClass().getName().substring(9)+" and should be a command such as MOVE or SAY.");
			return;
		}
		if (getTokens()[0] instanceof Move) {
			ACommandObjectMove move = parseMove();
			move.run();
		} else if (getTokens()[0] instanceof Say) {
			ACommandObjectSay say = parseSay();
			say.run();
		}
	}

	public ACommandObjectMove parseMove() {
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
			System.out.println("ERROR! 2nd argument in command MOVE should be a Word. You provided type "+getTokens()[1].getClass().getName().substring(9)+".");
		}
		return new ACommandObjectMove(avatar, x, y, canvas);
	}
	
	public ACommandObjectSay parseSay() {
		Avatar avatar = null;
		if (getTokens()[1] instanceof Word) {
			if (getTokens()[2] instanceof Quote) {
				avatar = (Avatar) avatars.get(getTokens()[1].getToken().toLowerCase());
			} else {
				System.out.println("ERROR! 3rd argument in command SAY should be a Quote. You provided type "+getTokens()[2].getClass().getName().substring(9)+".");
			}
		} else {
			System.out.println("ERROR! 2nd argument in command SAY should be a Word. You provided type "+getTokens()[1].getClass().getName().substring(9)+".");
		}
		return new ACommandObjectSay(avatar, getTokens()[2].getToken(), canvas);
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
	
	public void setString(String input) {
		scanner.setString(input);
		tokens = scanner.getTokens();
		history.addElement(tokens);
		interpretCommand();
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