package assignment10;

import bus.uigen.OEFrame;
import a10_commands.*;
import a10_commands.Thread;
import a10_token.*;
import a10_token.Number;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class AScanner implements Scanner {
	OEFrame OE;
	String string;
	String token;
	Token[] tokens = new Token[1];
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
	int pindex = 0;
	int index = 0;
	int strlen = 0;
	PropertyListenerSupport listeners = new APropertyListenerSupport();
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.add(listener);
		listener.propertyChange(new PropertyChangeEvent(this, "string", 0, getString()));
        listener.propertyChange(new PropertyChangeEvent(this, "tokens", 0, getTokens()));
	}
	
	public AScanner() {}
	public AScanner(String input) {
		setString(input);
	}
	
	public void setString(String input) {
		resetVars();
		setStrlen(input.length());
		string = input;
		processString();
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "string", getString(), getString()));
		listeners.notifyAllListeners(new PropertyChangeEvent(this, "tokens", getTokens(), getTokens()));
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
	
	private void resetVars() {
		tokens = new Token[1];
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
		pindex = 0;
		index = 0;
		strlen = 0;
	}
	
	@SuppressWarnings("unused")
	private int getIndex(String[] haystack, String needle) {
		for (int i = 0; i < haystack.length; i++) {
			if (haystack[i].equals(needle)) return i;
		}
		return -1;
	}
	
	private boolean isDigit(char token) {
		int digit = Character.getNumericValue(token);
		if (0 <= digit && 9 >= digit) return true;
		else return false;
	}
	
	private void setStrlen(int length) {
		strlen = length;
	}
	
	private int getStrlen() {
		return strlen;
	}
	
	private void setPrevIndex(int index) {
		pindex = index;
	}
	
	private int getPrevIndex() {
		return pindex;
	}
	
	private int getIndex() {
		return index;
	}
	
	private void setToken(String input) {
		token = input;
	}
	
	private String getToken() {
		return token;
	}
	
	private char getCToken() {
		return getString().charAt(getIndex());
	}
	
	private char getCToken(int index) {
		return getString().charAt(index);
	}
	
	private void checkPrevToken() {
		if (hasPrev()) checkToken(getIndex()-1);
	}
	
	private void checkToken(int index) {
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
	
	private void checkType() {
		char token = getCToken();
		if (token == '"') {
			inquote = !inquote;
		}
	}
	
	private boolean hasPrev() {
		if (0 < getIndex()) return true;
		else return false;
	}
	
	@SuppressWarnings("unused")
	private char getPrev() {
		return getCToken(getIndex()-1);
	}
	
	private boolean hasNext() {
		if (getIndex() < getStrlen()) return true;
		else return false;
	}
	
	private void nextToken() {
		++index;
	}
	
	private void appendTokenArray(Token instance) {
		int tokensLength = tokens.length;
		Token[] newTokens = new Token[tokensLength+1];
		for (int i = 0; i < tokensLength; i++) {
			newTokens[i] = tokens[i];
		}
		newTokens[tokensLength-1] = instance;
		setTokens(newTokens);
		tindex++;
	}
	
	private void storeToken() {
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
		        	appendTokenArray(new Move(word));
	            break;
		        case "say":
		        	appendTokenArray(new Say(word));
	            break;
		        case "rotateleftarm":
		        	appendTokenArray(new RotateLeftArm(word));
	            break;
		        case "rotaterightarm":
		        	appendTokenArray(new RotateRightArm(word));
	            break;
		        case "repeat":
		        	appendTokenArray(new Repeat(word));
	            break;
		        case "define":
		        	appendTokenArray(new Define(word));
	            break;
		        case "call":
		        	appendTokenArray(new Call(word));
	            break;
		        case "thread":
		        	appendTokenArray(new Thread(word));
	            break;
		        case "wait":
		        	appendTokenArray(new Wait(word));
	            break;
		        case "proceedall":
		        	appendTokenArray(new Proceedall(word));
	            break;
		        case "sleep":
		        	appendTokenArray(new Sleep(word));
	            break;
		        case "undo":
		        	appendTokenArray(new Undo(word));
	            break;
		        case "redo":
		        	appendTokenArray(new Redo(word));
	            break;
	            default:
	            	appendTokenArray(new Word(getToken()));
	            break;
            }
		} else if (isnumber) {
			appendTokenArray(new Number(getToken()));
		} else if (isillegal) {
			errors[eindex++] = getToken();
		}
	}
	
	private void processString() {
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
	}
}