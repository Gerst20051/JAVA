package commandpackage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

import objectpackage.ABackground;
import objectpackage.ARotatingLine;
import objectpackage.AnImage;
import objectpackage.ImageInterface;

import main.APropertyListenerSupport;
import main.PropertyListenerSupport;
import compositepackage.*;
import scannerpackage.*;
import table.ATable;
import table.TableInterface;
import tokenpackage.*;
import tokenpackage.Number;
import util.annotations.ObserverRegisterer;
import util.annotations.ObserverTypes;

public class ACommandInterpreter implements CommandInterpreter {
	OzScene ozscene;
	AScanner scanner;
	String command,dorothy,scarecrow,oz;
	GeneralTokenInterface[] tokenarray;
	TableInterface table = new ATable();
	
	public ACommandInterpreter(OzScene initozscene, AScanner initscanner){
		ozscene = initozscene;
		scanner = initscanner;
		setAvatar();
	}
	
	
	public void setAvatar(){
		table.put("dorothy", ozscene.getDorothy());
		table.put("scarecrow", ozscene.getScarecrow());
		table.put("oz", ozscene.getOz());
	}
	
	public GeneralTokenInterface[] getTokens() {
		return tokenarray;
	}
	
	public void setcommand(String newcommand) {
		command = newcommand;
		scanner.setString(command);
		tokenarray = scanner.getArray();
		System.out.println(getTokens()[0].getClass());
		System.out.println(getTokens()[1].getClass());
		System.out.println(getTokens()[2].getClass());
		if (tokenarray[0] instanceof ACommand) {
			System.out.println("COMMAND!");
		} else {
			System.out.println("NOT A COMMAND!");
		}
		
		if (tokenarray[0] instanceof say){
			if(tokenarray[1] instanceof Word) {
				Avatar avatar = (Avatar) table.get(tokenarray[1].getTokenString().toLowerCase());
				if(tokenarray[2] instanceof QuotedString) {
					avatar.setText(tokenarray[2].getTokenString());
				}
			}
		}
		if (tokenarray[0] instanceof move){
			if(tokenarray[1] instanceof Word) {
				AnAvatar avatar = (AnAvatar) table.get(tokenarray[1].getTokenString().toLowerCase());
				if(tokenarray[2] instanceof Number){
					avatar.setX(Integer.parseInt(tokenarray[2].getTokenString()));
					
					if(tokenarray[3] instanceof Number){
						avatar.setY(Integer.parseInt(tokenarray[3].getTokenString()));
						avatar.getHead();
						avatar.getTorso();
						avatar.getArms();
						avatar.getLegs();
											
					}
				}
			}
		}
	}

	}
	

