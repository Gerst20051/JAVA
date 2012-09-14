package scanner;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class Scanner {
	String string;
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
	String token;
	int pindex = 0;
	int index = 0;
	int strlen = 0;
	
	token.Minus minus = new token.Minus();
	token.Plus plus = new token.Plus();
	token.Start start = new token.Start();
	token.End end = new token.End();
	token.Quote quote = new token.Quote();
	token.Word word = new token.Word();
	token.Number number = new token.Number();
	
	public Scanner() {}
	public Scanner(String input) {
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
	
	void printToken() {
		if (issign) {
			if (getCToken() == '-') {
				minus.setMinus(getToken());
				System.out.println("<Token Minus>");
				System.out.println(minus.getMinus());
			} else {
				plus.setPlus(getToken());
				System.out.println("<Token Plus>");
				System.out.println(plus.getPlus());
			}
		} else if (isanchor) {
			if (getCToken() == '{') {
				start.setStart(getToken());
				System.out.println("<Token Start>");
				System.out.println(start.getStart());
			} else {
				end.setEnd(getToken());
				System.out.println("<Token End>");
				System.out.println(end.getEnd());
			}
		} else if (isquote) {
			quote.setQuote(getToken());
			System.out.println("<Token Quote>");
			System.out.println(quote.getQuote());
		} else if (isword) {
			word.setWord(getToken());
			System.out.println("<Token Word>");
			System.out.println(word.getWord());
			System.out.println(word.getLowercaseWord());
		} else if (isnumber) {
			number.setStringNumber(getToken());
			System.out.println("<Token Number>");
			System.out.println(number.getStringNumber());
			System.out.println(number.getNumber());
		} else if (isillegal) {
			System.out.println("<Token Illegal>");
			System.out.println(getToken());
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
}