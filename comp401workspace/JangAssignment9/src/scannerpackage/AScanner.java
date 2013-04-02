package scannerpackage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import commandpackage.*;
import tokenpackage.*;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import tokenpackage.Number;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)

public class AScanner {
	int arrayIndex = 0;
	GeneralTokenInterface[] array;
	String mystring;
	
	public AScanner(String myNewString) {
		setString(myNewString);

	}

	public AScanner() {
		mystring = "empty";
	}
	PropertyListenerSupport propertyListenerSupport = new APropertyListenerSupport();
	
	@ObserverRegisterer(ObserverTypes.PROPERTY_LISTENER)
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyListenerSupport.add(listener);
	}
	
	public GeneralTokenInterface[] getArray() {
		GeneralTokenInterface[] newArray = new GeneralTokenInterface[arrayIndex];
		for(int i=0; i < arrayIndex; i++){
			newArray[i] = array[i];
		}
		return newArray;
	}
	
	// myCharacterDigit
	public static boolean myCharacterDigit(char args) {
		return args >= '0' && args <= '9';
	}

	public String getString() {
		return mystring;
	}

	public void setString(String newstring) {
		setArray(newstring);
		mystring = newstring;
		propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "String",getString(),getString()));
	    
	}

	public void setArray(String mystring) {

		int indexStart = 0;
		int indexEnd = 0;
		char doublequote = '"';
		array = new GeneralTokenInterface[50];
		arrayIndex = 0;
		
		while (indexEnd < mystring.length()) {

			if (Character.isLetter(mystring.charAt(indexEnd))) {
				indexStart = indexEnd;

				while (indexEnd < mystring.length()
						&& Character.isLetter(mystring.charAt(indexEnd))) {
					indexEnd = indexEnd + 1;
				}
				String myWord = mystring.substring(indexStart, indexEnd);
				
				
				if (myWord.equalsIgnoreCase("call")){
					call mycommand = new call(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("define")){
					define mycommand = new define(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("move")){
					move mycommand = new move(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("proceedall")){
					proceedall mycommand = new proceedall(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("redo")){
					redo mycommand = new redo(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("repeat")){
					repeat mycommand = new repeat(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("rotateLeftArm")){
					rotateLeftArm mycommand = new rotateLeftArm(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("rotateRightArm")){
					rotateRightArm mycommand = new rotateRightArm(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("say")){
					say mycommand = new say(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("sleep")){
					sleep mycommand = new sleep(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("thread")){
					thread mycommand = new thread(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("undo")){
					undo mycommand = new undo(myWord);
					array[arrayIndex] = mycommand;
				}
				else if (myWord.equalsIgnoreCase("wait")){
					wait mycommand = new wait(myWord);
					array[arrayIndex] = mycommand;
				}
				else {
					Word myWordObject = new Word(myWord);
					array[arrayIndex] = myWordObject;
				}  
		
				arrayIndex++;
				indexEnd--;
			} 
			else if (myCharacterDigit(mystring.charAt(indexEnd))) {
				indexStart = indexEnd;

				while (indexEnd < mystring.length()
						&& myCharacterDigit(mystring.charAt(indexEnd))) {
					indexEnd = indexEnd + 1;
				}
				Number myNumber = new Number(mystring.substring(indexStart,
						indexEnd));
				array[arrayIndex] = myNumber;
				arrayIndex++;
				indexEnd--;
			} 

			else if (mystring.charAt(indexEnd) == doublequote) {
				indexStart = indexEnd + 1;
				indexEnd = indexEnd + 1;
				while ((mystring.charAt(indexEnd) != doublequote)) {
					indexEnd = indexEnd + 1;

					if ((indexEnd + 1) > mystring.length()) {
						System.out.println("We are missing the double quote!");
						System.exit(-1);
					}

				}
				if ((indexEnd) < mystring.length()) {
					QuotedString newQuote = new QuotedString(
							mystring.substring(indexStart - 1, indexEnd + 1));
					array[arrayIndex] = newQuote;
					arrayIndex++;
				}
			} // end of if
			else if ((mystring.charAt(indexEnd) == '{')) {
				StartToken newStartToken = new StartToken("{");
				array[arrayIndex] = newStartToken;
				arrayIndex++;
			}

			else if (mystring.charAt(indexEnd) == '+') {
				Plus newPlus = new Plus("+");
				array[arrayIndex] = newPlus;
				arrayIndex++;
			} else if (mystring.charAt(indexEnd) == '-') {
				Minus newMinus = new Minus("-");
				array[arrayIndex] = newMinus;
				arrayIndex++;
			}

			else if (mystring.charAt(indexEnd) == '}') {
				EndToken newEndToken = new EndToken("}");
				array[arrayIndex] = newEndToken;
				arrayIndex++;
			
			}

		
			indexEnd++;
			propertyListenerSupport.notifyAllListeners(new PropertyChangeEvent(this, "Array",getArray(),getArray()));
			
		} 
	}
	
}

