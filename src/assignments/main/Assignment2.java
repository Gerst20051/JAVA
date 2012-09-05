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
	//static char token;
	static int oindex = 0;
	static int index = 0;
	static int strlen = 0;
	
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
		} else if (Character.isLetter(ctoken)) {
			isword = true;
		} else if (Character.isDigit(ctoken)) {
			isnumber = true;
		} else if (ctoken == '-' || ctoken == '+') {
			issign = true;
		} else if (ctoken == '"') {
			isquote = true;
		}
	}
	
	static void checkType() {
		char token = getToken(getIndex());
		if (Character.isLetter(token)) {
			inword = true;
		} else if (Character.isDigit(token)) {
			innumber = true;
		} else if (token == '"') {
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
		if (getIndex() < strlen) return true;
		else return false;
	}
	
	static void nextToken() {
		++index;
	}
	
	static void processString(String data) {
		while (hasNext()) {
			checkType();
			if (inquote) {
				
			} else {
				if (isword) {
					//input.substring(oindex,index);
				} else if (isnumber) {
					
				} else if (issign) {
					
				}
			}
			nextToken();
		}
	}
	
	public static void main(String[] args) {
		while (!(input = Console.readString()).equals(".")) {
			strlen = input.length();
			if (1 < strlen) processString(input);
		}
	}
}