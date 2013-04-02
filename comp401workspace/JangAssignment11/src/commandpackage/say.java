package commandpackage;

import tokenpackage.Token;

public class say implements Token, Command {
	public say(String input) {
		setTokenString(input);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTokenString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTokenString(String newgenericstring) {
		// TODO Auto-generated method stub
		
	}
}