package assignment7;

import bus.uigen.OEFrame;
import a7_token.*;
import a7_token.Number;
import a7_commands.*;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class AParser implements Parser {
	OEFrame OE;
	Canvas canvas;
	AScanner scanner;
	Token[] tokens;
	ATable avatars = new ATable();
	AObjectDatabase history = new AObjectDatabase();
	
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
	
	private void interpretCommand() {
		if (!(getTokens()[0] instanceof Command)) {
			System.out.println("ERROR! 1st argument in string you provided is of type "+getTokens()[0].getClass().getName().substring(9)+" and should be a command such as MOVE or SAY.");
			return;
		}
		if (getTokens()[0] instanceof Move) {
			if (getTokens()[1] instanceof Word) {
				Avatar avatar = (Avatar) avatars.get(getTokens()[1].getToken().toLowerCase());
				if (4 < getTokens().length-1) {
					if (!(getTokens()[2] instanceof Number)) {
						if ((getTokens()[4] instanceof Number)) {
							int x = ((Number) getTokens()[3]).getNumber();
							if (getTokens()[2] instanceof Minus) x = -x;
							int y = ((Number) getTokens()[4]).getNumber();
							avatar.moveLocation(x,y);
						} else {
							int x = ((Number) getTokens()[3]).getNumber();
							if (getTokens()[2] instanceof Minus) x = -x;
							int y = ((Number) getTokens()[5]).getNumber();
							if (getTokens()[4] instanceof Minus) y = -y;
							avatar.moveLocation(x,y);
						}
					} else if (!(getTokens()[3] instanceof Number)) {
						int x = ((Number) getTokens()[2]).getNumber();
						int y = ((Number) getTokens()[4]).getNumber();
						if (getTokens()[3] instanceof Minus) y = -y;
						avatar.moveLocation(x,y);
					}
				} else {
					if (getTokens()[2] instanceof Number && getTokens()[3] instanceof Number) {
						avatar.moveLocation(((Number) getTokens()[2]).getNumber(),((Number) getTokens()[3]).getNumber());
					} else {
						if (!(getTokens()[2] instanceof Number) && !(getTokens()[3] instanceof Number)) {
							System.out.println("ERROR! 3rd and 4th arguments in command MOVE should be integers. You provided type "+getTokens()[2].getClass().getName().substring(9)+" and "+getTokens()[3].getClass().getName().substring(9)+".");
						} else if (!(getTokens()[2] instanceof Number)) {
							System.out.println("ERROR! 3rd argument in command MOVE should be an integer. You provided type "+getTokens()[2].getClass().getName().substring(9)+".");
						} else if (!(getTokens()[3] instanceof Number)) {
							System.out.println("ERROR! 4th argument in command MOVE should be an integer. You provided type "+getTokens()[3].getClass().getName().substring(9)+".");
						}
						return;
					}
				}
				OE.refresh();
			} else {
				System.out.println("ERROR! 2nd argument in command MOVE should be a Word. You provided type "+getTokens()[1].getClass().getName().substring(9)+".");
				return;
			}
		} else if (getTokens()[0] instanceof Say) {
			if (getTokens()[1] instanceof Word) {
				if (getTokens()[2] instanceof Quote) {
					Avatar avatar = (Avatar) avatars.get(getTokens()[1].getToken().toLowerCase());
					avatar.say(getTokens()[2].getToken(), 3);
				} else {
					System.out.println("ERROR! 3rd argument in command SAY should be a Quote. You provided type "+getTokens()[2].getClass().getName().substring(9)+".");
					return;
				}
			} else {
				System.out.println("ERROR! 2nd argument in command SAY should be a Word. You provided type "+getTokens()[1].getClass().getName().substring(9)+".");
				return;
			}
		}
	}
	
	public void setString(String input) {
		scanner.setString(input);
		tokens = scanner.getTokens();
		history.addElement(tokens);
		interpretCommand();
	}
	
	public void reference(OEFrame object) {
		OE = object;
	}
}