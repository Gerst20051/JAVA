package swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame {
	MainWindow(String title) {
		this.setTitle(title);
		this.setVisible(true);
		this.setMinimumSize(new Dimension(300, 350));
		this.setLayout(new GridBagLayout());
		JButton btnA = new JButton("Click Me!");
		this.add(btnA);
		btnA.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button has been clicked!");
			}
		});
	}
	
	public static void main(String[] args) {
		new MainWindow("Title");
	}
}