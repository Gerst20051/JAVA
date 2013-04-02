package a4_scanner;

import bus.uigen.OEFrame;
import a4_token.*;
import a4_token.Number;
import a4_commands.*;
import a4_commands.Thread;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Scanner {
	OEFrame OE;
	String string;
	String token;
	Token[] tokens = new Token[1];
	Token[] commands = new Token[1];
	String[] errors = new String[50];
	boolean isspace = false;
	boolean issign = false;
	boolean isanchor = false;
	boolean isquote = false;
	boolean isword = false;
	boolean isnumber = false;
	boolean isillegal = false;
	boolean inword = false;
	boolean innumber = false;
	boolean inquote = false;
	final char space = ' ';
	int eindex = 0;
	int tindex = 0;
	int cindex = 0;
	int pindex = 0;
	int index = 0;
	int strlen = 0;
	
	public Scanner() {}
	public Scanner(String input) {
		setString(input);
	}
	
	public void setString(String input) {
		resetVars();
		setStrlen(input.length());
		string = input;
		processString();
	}
	
	public String getString() {
		return string;
	}
	
	public void setTokens(Token[] input) {
		tokens = input;
	}
	
	public Token[] getTokens() {
		return tokens;
	}
	
	public void setCommands(Token[] input) {
		commands = input;
	}
	
	public Token[] getCommands() {
		return commands;
	}
	
	void resetVars() {
		tokens = new Token[1];
		commands = new Token[1];
		errors = new String[50];
		isspace = false;
		issign = false;
		isanchor = false;
		isquote = false;
		isword = false;
		isnumber = false;
		isillegal = false;
		inword = false;
		innumber = false;
		inquote = false;
		eindex = 0;
		tindex = 0;
		cindex = 0;
		pindex = 0;
		index = 0;
		strlen = 0;
	}
	
	int getIndex(String[] haystack, String needle) {
		for (int i = 0; i < haystack.length; i++) {
			if (haystack[i].equals(needle)) return i;
		}
		return -1;
	}
	
	boolean isDigit(char token) {
		int digit = Character.getNumericValue(token);
		if (0 <= digit && 9 >= digit) return true;
		else return false;
	}
	
	void setStrlen(int length) {
		strlen = length;
	}
	
	int getStrlen() {
		return strlen;
	}
	
	void setPrevIndex(int index) {
		pindex = index;
	}
	
	int getPrevIndex() {
		return pindex;
	}
	
	int getIndex() {
		return index;
	}
	
	void setToken(String input) {
		token = input;
	}
	
	String getToken() {
		return token;
	}
	
	char getCToken() {
		return getString().charAt(getIndex());
	}
	
	char getCToken(int index) {
		return getString().charAt(index);
	}
	
	void checkPrevToken() {
		if (hasPrev()) checkToken(getIndex()-1);
	}
	
	void checkToken(int index) {
		isspace = false;
		issign = false;
		isanchor = false;
		isquote = false;
		isword = false;
		isnumber = false;
		isillegal = false;
		char ctoken = getCToken(index);
		if (ctoken == space) {
			isspace = true;
		} else if (ctoken == '-' || ctoken == '+') {
			issign = true;
		} else if (ctoken == '{' || ctoken == '}') {
			isanchor = true;
		} else if (ctoken == '"') {
			isquote = true;
		} else if (Character.isLetter(ctoken)) {
			isword = true;
		} else if (isDigit(ctoken)) {
			isnumber = true;
		} else {
			isillegal = true;
		}
	}
	
	void checkType() {
		char token = getCToken();
		if (token == '"') {
			inquote = !inquote;
		}
	}
	
	boolean hasPrev() {
		if (0 < getIndex()) return true;
		else return false;
	}
	
	char getPrev() {
		return getCToken(getIndex()-1);
	}
	
	boolean hasNext() {
		if (getIndex() < getStrlen()) return true;
		else return false;
	}
	
	void nextToken() {
		++index;
	}
	
	void appendTokenArray(Token instance) {
		int tokensLength = tokens.length;
		Token[] newTokens = new Token[tokensLength+1];
		for (int i = 0; i < tokensLength; i++) {
			newTokens[i] = tokens[i];
		}
		newTokens[tokensLength] = instance;
		setTokens(newTokens);
		tindex++;
	}
	
	void appendCommandArray(Token instance) {
		int commandsLength = commands.length;
		Token[] newCommands = new Token[commandsLength+1];
		for (int i = 0; i < commandsLength; i++) {
			newCommands[i] = commands[i];
		}
		newCommands[commandsLength] = instance;
		setCommands(newCommands);
		cindex++;
	}
	
	void storeToken() {
		if (issign) {
			if (getCToken() == '-') {
				appendTokenArray(new Minus(getToken()));
			} else {
				appendTokenArray(new Plus(getToken()));
			}
		} else if (isanchor) {
			if (getCToken() == '{') {
				appendTokenArray(new Start(getToken()));
			} else {
				appendTokenArray(new End(getToken()));
			}
		} else if (isquote) {
			appendTokenArray(new Quote(getToken()));
		} else if (isword) {
			String word = new Word(getToken()).getLowercaseWord();
	        switch (word) {
		        case "move":
		        	appendCommandArray(new Move(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "say":
		        	appendCommandArray(new Say(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "rotateleftarm":
		        	appendCommandArray(new RotateLeftArm(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "rotaterightarm":
		        	appendCommandArray(new RotateRightArm(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "repeat":
		        	appendCommandArray(new Repeat(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "define":
		        	appendCommandArray(new Define(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "call":
		        	appendCommandArray(new Call(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "thread":
		        	appendCommandArray(new Thread(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "wait":
		        	appendCommandArray(new Wait(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "proceedall":
		        	appendCommandArray(new Proceedall(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "sleep":
		        	appendCommandArray(new Sleep(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "undo":
		        	appendCommandArray(new Undo(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
		        case "redo":
		        	appendCommandArray(new Redo(word));
	            	System.out.println("command " + commands[cindex].getToken());
	            break;
	            default:
	            	appendTokenArray(new Word(getToken()));
	            	System.out.println("word " + tokens[tindex].getToken());
	            break;
            }
		} else if (isnumber) {
			appendTokenArray(new Number(getToken()));
		} else if (isillegal) {
			errors[eindex++] = getToken();
		}
	}
	
	void processString() {
		while (hasNext()) {
			checkType();
			checkToken(getIndex());
			if (!inquote && !isquote && 1 < getIndex()) {
				if (isspace || isillegal || issign || isanchor) {
					if (issign || isanchor || isillegal) {
						if (getPrevIndex() < getIndex()) {
							setToken(getString().substring(getPrevIndex(),getIndex()));
							checkPrevToken();
							storeToken();
						}
						checkToken(getIndex());
						setToken(getString().substring(getIndex(),getIndex()+1));
					} else {
						setToken(getString().substring(getPrevIndex(),getIndex()));
						checkPrevToken();
					}
					setPrevIndex(getIndex()+1);
					storeToken();
				}
			}
			if (getIndex() == getStrlen()-1) {
				if (!issign && !isanchor && !isillegal) {
					setToken(getString().substring(getPrevIndex(),getStrlen()));
					storeToken();
				}
				if (inquote) {
					setToken(getString().substring(getPrevIndex(),getStrlen()));
					storeToken();
					System.out.println("error: please end quote");
				}
			}
			nextToken();
			OE.refresh();
		}
	}
	
	public void reference(OEFrame editor) {
		OE = editor;
	}
}