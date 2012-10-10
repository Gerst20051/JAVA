package assignment7;

import a7_token.*;
import a7_token.Number;
import a7_commands.*;
import a7_commands.Thread;
import util.annotations.StructurePattern;
@StructurePattern("Bean Pattern")

public class Parser {
	ATable avatars = new ATable();
	Canvas canvas;
	Scanner scanner;
	private Token[] tokens;
	
	public Parser(Canvas canvas, Scanner scanner) {
		this.canvas = canvas;
		this.scanner = scanner;
		setAvatars();
	}
	
	public Token[] getTokens() {
		return tokens;
	}
	
	private void setAvatars() {
		avatars.put("dorothy", canvas.getDorothyAvatar());
		avatars.put("scarecrow", canvas.getScarecrowAvatar());
		avatars.put("wizard", canvas.getWizardAvatar());
	}
	
	public void interpretCommand() {
		for (int i = 0; i < getTokens().length-1; i++) {
			Class cls = getTokens()[i].getClass();
			System.out.println(cls);
			System.out.println(cls.getName());
			if (getTokens()[i] instanceof Move) {
				System.out.println("move instance");
			}
		}
	}
	
	public void setString(String input) {
		scanner.setString(input);
		tokens = scanner.getTokens();
		interpretCommand();
	}
}