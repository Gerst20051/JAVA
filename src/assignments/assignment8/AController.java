package assignment8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AController implements Controller, ActionListener {
	JFrame frame;
	JTextField command;
	AParser parser;
	
	AController(AParser aparser, JTextField thecommand) {
		parser = aparser;
		command = thecommand;
		command.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		parser.setString(command.getText());
	}
	
	public void setModel(AParser theparser) {
		parser = theparser;
	}
}