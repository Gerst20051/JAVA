package assignment10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class AController implements Controller, ActionListener {
	JFrame frame;
	TextComponentInterface textcomponent;
	AParser parser;
	
	AController(AParser aparser, TextComponentInterface thetextcomponent) {
		parser = aparser;
		textcomponent = thetextcomponent;
		textcomponent.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		parser.setString(textcomponent.getText());
	}
	
	public void setModel(AParser theparser) {
		parser = theparser;
	}
}