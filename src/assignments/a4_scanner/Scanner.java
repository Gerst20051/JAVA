package a4_scanner;

import a4_token.*;
import a4_token.Number;
import a4_commands.*;
import a4_commands.Thread;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Scanner {
	String string;
	String token;
	Token[] tokens = new Token[7];
	Token[] commands = new Token[13];
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
		tokens[tindex++] = new Minus();
		tokens[tindex++] = new Plus();
		tokens[tindex++] = new Start();
		tokens[tindex++] = new End();
		tokens[tindex++] = new Quote();
		tokens[tindex++] = new Word();
		tokens[tindex++] = new Number();
		commands[cindex++] = new Move();
		commands[cindex++] = new Say();
		commands[cindex++] = new RotateLeftArm();
		commands[cindex++] = new RotateRightArm();
		commands[cindex++] = new Repeat();
		commands[cindex++] = new Define();
		commands[cindex++] = new Call();
		commands[cindex++] = new Thread();
		commands[cindex++] = new Wait();
		commands[cindex++] = new Proceedall();
		commands[cindex++] = new Sleep();
		commands[cindex++] = new Undo();
		commands[cindex++] = new Redo();
		setString(input);
	}
	
	public void setString(String input) {
		setStrlen(input.length());
		string = input;
		processString();
	}
	
	public String getString() {
		return string;
	}
	
	public Token[] getTokens() {
		return tokens;
	}
	
	public Token[] getCommands() {
		return commands;
	}
	
	void resetVars() {
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
	
	void storeToken() {
		if (issign) {
			if (getCToken() == '-') {
				tokens[0].setToken(getToken());
			} else {
				tokens[1].setToken(getToken());
			}
		} else if (isanchor) {
			if (getCToken() == '{') {
				tokens[2].setToken(getToken());
			} else {
				tokens[3].setToken(getToken());
			}
		} else if (isquote) {
			tokens[4].setToken(getToken());
		} else if (isword) {
			tokens[5].setToken(getToken());
			String word = ((Word) tokens[5]).getLowercaseWord();
	        switch (word) {
		        case "move":
	            	commands[0].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "say":
		        	commands[1].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "rotateleftarm":
		        	commands[2].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "rotaterightarm":
		        	commands[3].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "repeat":
		        	commands[4].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "define":
		        	commands[5].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "call":
		        	commands[6].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "thread":
		        	commands[7].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "wait":
		        	commands[8].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "proceedall":
		        	commands[9].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "sleep":
		        	commands[10].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "undo":
		        	commands[11].setToken(word);
	            	System.out.println("command "+word);
	            break;
		        case "redo":
		        	commands[12].setToken(word);
	            	System.out.println("command "+word);
	            break;
	            default:
	            	tokens[5].setToken(getToken());
	            	System.out.println("word " + tokens[5].getToken());
	            break;
            }
		} else if (isnumber) {
			tokens[6].setToken(getToken());
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
		}
		resetVars();
	}
}