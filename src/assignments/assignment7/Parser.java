package assignment7;

import a4_token.Token;

public class Parser {
	Canvas canvas;
	Scanner scanner;
	private Token[] tokens;
	private Token[] commands;
	
	public Parser(Canvas canvas, Scanner scanner) {
		this.canvas = canvas;
		this.scanner = scanner;
	}
	
	public Token[] getTokens() {
		return tokens;
	}
	
	public Token[] getCommands() {
		return commands;
	}
	
	public void setString(String input) {
		scanner.setString(input);
		tokens = scanner.getTokens();
		commands = scanner.getCommands();
	}
}