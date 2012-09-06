package main;

import console.Console;

public class Assignment2 {
	static String input;
	static boolean isspace = false;
	static boolean isword = false;
	static boolean isnumber = false;
	static boolean issign = false;
	static boolean isquote = false;
	static boolean inword = false;
	static boolean innumber = false;
	static boolean inquote = false;
	final static char space = ' ';
	static String token;
	static int pindex = 0;
	static int index = 0;
	static int strlen = 0;
	
	static void resetVars() {
		pindex = 0;
		index = 0;
		strlen = 0;
	}
	
	static boolean isDigit(char token) {
		int digit = Integer.parseInt(Character.toString(token));
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
	
	static char getToken(int index) {
		return input.charAt(index);
	}
	
	static void checkPrevToken() {
		if (hasPrev()) checkToken(getIndex()-1);
	}
	
	static void checkToken(int index) {
		isspace = false;
		isword = false;
		isnumber = false;
		issign = false;
		isquote = false;
		char ctoken = getToken(index);
		if (ctoken == space) {
			isspace = true;
		} else if (ctoken == '-' || ctoken == '+') {
			issign = true;
		} else if (ctoken == '"') {
			isquote = true;
		} else if (Character.isLetter(ctoken)) {
			isword = true;
		} else if (isDigit(ctoken)) {
			isnumber = true;
		}
	}
	
	static void checkType() {
		char token = getToken(getIndex());
		if (token == '"') {
			inquote = !inquote;
		}
	}
	
	static boolean hasPrev() {
		if (0 < getIndex()) return true;
		else return false;
	}
	
	static char getPrev() {
		return getToken(getIndex()-1);
	}
	
	static boolean hasNext() {
		if (getIndex() < getStrlen()) return true;
		else return false;
	}
	
	static void nextToken() {
		++index;
	}
	
	static void printToken() {
		if (isquote) {
			System.out.println("isquote: "+token);
		} else if (isword) {
			System.out.println("isword: "+token);
		} else if (isnumber) {
			System.out.println("isnumber: "+token);
		} else if (issign) {
			System.out.println("issign: "+token);
		}
	}
	
	static void processString(String data) {
		while (hasNext()) {
			checkType();
			checkToken(getIndex());
			if (!inquote && !isquote) {
				if (isspace && 1 < getIndex()) {
					token = input.substring(getPrevIndex(),getIndex());
					checkPrevToken();
					printToken();
					setPrevIndex(getIndex()+1);
				} else {
					
				}
			}
			if (getIndex() == getStrlen()-1) {
				token = input.substring(getPrevIndex(),getIndex()+1);
				checkPrevToken();
				printToken();
			}
			nextToken();
		}
		resetVars();
	}
	
	public static void main(String[] args) {
		// move Dorothy 30 40 say oz "one" say "2" "$%!@"
		while (!(input = Console.readString()).equals(".")) {
			setStrlen(input.length());
			if (1 < getStrlen()) processString(input);
		}
	}
}