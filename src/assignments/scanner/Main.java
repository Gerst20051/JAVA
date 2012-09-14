package scanner;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Main {
	static String string;
	static boolean isspace = false;
	static boolean issign = false;
	static boolean isanchor = false;
	static boolean isquote = false;
	static boolean isword = false;
	static boolean isnumber = false;
	static boolean isillegal = false;
	static boolean inword = false;
	static boolean innumber = false;
	static boolean inquote = false;
	final static char space = ' ';
	static String token;
	static int pindex = 0;
	static int index = 0;
	static int strlen = 0;
	
	public Main() {}
	public Main(String input) {
		System.out.println("scanner constructor");
		setString(input);
	}
	
	public void setString(String input) {
		setStrlen(input.length());
		string = input;
		processString();
	}
	
	public static String getString() {
		return string;
	}
	
	static void resetVars() {
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
	
	static boolean isDigit(char token) {
		int digit = Character.getNumericValue(token);
		if (0 <= digit && 9 >= digit) return true;
		else return false;
	}
	
	static void setStrlen(int length) {
		strlen = length;
	}
	
	static int getStrlen() {
		return strlen;
	}
	
	static void setPrevIndex(int index) {
		pindex = index;
	}
	
	static int getPrevIndex() {
		return pindex;
	}
	
	static int getIndex() {
		return index;
	}
	
	static void setToken(String input) {
		token = input;
	}
	
	static String getToken() {
		return token;
	}
	
	static char getCToken() {
		return getString().charAt(getIndex());
	}
	
	static char getCToken(int index) {
		return getString().charAt(index);
	}
	
	static void checkPrevToken() {
		if (hasPrev()) checkToken(getIndex()-1);
	}
	
	static void checkToken(int index) {
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
	
	static void checkType() {
		char token = getCToken();
		if (token == '"') {
			inquote = !inquote;
		}
	}
	
	static boolean hasPrev() {
		if (0 < getIndex()) return true;
		else return false;
	}
	
	static char getPrev() {
		return getCToken(getIndex()-1);
	}
	
	static boolean hasNext() {
		if (getIndex() < getStrlen()) return true;
		else return false;
	}
	
	static void nextToken() {
		++index;
	}
	
	static void printToken() {
		if (issign) {
			System.out.println("issign: "+getToken());
		} else if (isanchor) {
			System.out.println("isanchor: "+getToken());
		} else if (isquote) {
			System.out.println("isquote: "+getToken());
		} else if (isword) {
			System.out.println("isword: "+getToken());
		} else if (isnumber) {
			System.out.println("isnumber: "+getToken());
		} else if (isillegal) {
			System.out.println("illegal: "+getToken());
		}
	}
	
	static void processString() {
		while (hasNext()) {
			checkType();
			checkToken(getIndex());
			if (!inquote && !isquote && 1 < getIndex()) {
				if (isspace || isillegal || issign || isanchor) {
					if (issign || isanchor || isillegal) {
						if (getPrevIndex() < getIndex()) {
							setToken(getString().substring(getPrevIndex(),getIndex()));
							checkPrevToken();
							printToken();
						}
						checkToken(getIndex());
						setToken(getString().substring(getIndex(),getIndex()+1));
					} else {
						setToken(getString().substring(getPrevIndex(),getIndex()));
						checkPrevToken();
					}
					setPrevIndex(getIndex()+1);
					printToken();
				}
			}
			if (getIndex() == getStrlen()-1) {
				if (!issign && !isanchor && !isillegal) {
					setToken(getString().substring(getPrevIndex(),getStrlen()));
					printToken();
				}
				if (inquote) {
					setToken(getString().substring(getPrevIndex(),getStrlen()));
					printToken();
					System.out.println("error: please end quote");
				}
			}
			nextToken();
		}
		resetVars();
	}
	
	public static void main(String[] args) {
		System.out.println("scanner class");
	}
}