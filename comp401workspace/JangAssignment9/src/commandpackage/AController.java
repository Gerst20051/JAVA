package commandpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JTextField;



public class AController implements Controller,
		ActionListener {
	
	JFrame frame;
//	JTextField command;
	TextComponentInterface textcomponent;

	CommandInterpreter commandinterpreter;

	public AController(CommandInterpreter thecommandinterpreter, TextComponentInterface thetextcomponent) {
		commandinterpreter = thecommandinterpreter;
		textcomponent = thetextcomponent;
		textcomponent.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// JTextField source = (JTextField) event.getSource();
		// String command = source.getText();
		commandinterpreter.setcommand(textcomponent.getText());

	}

	public void setModel(CommandInterpreter theCommandInterpreter) {
		commandinterpreter = theCommandInterpreter;
	}

}
