package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import commandpackage.CommandInterpreter;

public class AController implements Controller, ActionListener {
	JFrame frame;
	JTextField command;
	
	CommandInterpreter commandinterpreter;
	
	AController(CommandInterpreter thecommandinterpreter, JTextField thecommand){
		commandinterpreter = thecommandinterpreter; 
		command = thecommand; 
		command.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
//		JTextField source = (JTextField) event.getSource();
//		String command = source.getText();
		commandinterpreter.setcommand(command.getText());

	}
	
	public void setModel(CommandInterpreter theCommandInterpreter) {
		commandinterpreter = theCommandInterpreter; 
	}
	
}
