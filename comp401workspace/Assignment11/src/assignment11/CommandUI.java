package assignment11;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CommandUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnA = new JButton("Run!");
	TextComponentInterface txtA = (TextComponentInterface) new JTextField();
	AParser parser;
	
	CommandUI(AParser model) {
		this.setTitle("Command Interpreter");
		this.setVisible(true);
		this.setMinimumSize(new Dimension(300, 350));
		this.setLayout(new GridLayout(2,1));
		this.add((Component) txtA);
		this.add(btnA);
		AController aController = new AController(model, txtA);
		aController.setModel(model);
		
		btnA.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				printText();
			}
		});
		
		/*
		ObservableCounter model = new AnObservableCounter();
        CounterObserver view = new ACounterJTextFieldView(textField);
        model.addObserver(view);
        CounterController controller = new ACounterJButtonController(button);
        controller.setModel(model);
        */
	}
	
	public void printText() {
		System.out.println("Value: "+txtA.getText());
	}
}