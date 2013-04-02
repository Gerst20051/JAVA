package main;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bus.uigen.ObjectEditor;

import objectpackage.ABackground;
import objectpackage.ARotatingLine;
import objectpackage.AnImage;
import objectpackage.ImageInterface;

import scannerpackage.AScanner;
import util.misc.ThreadSupport;

import commandpackage.ACommandInterpreter;
import commandpackage.CommandInterpreter;
import compositepackage.AOzScene;
import compositepackage.AnAngle;
import compositepackage.AnAvatar;
import compositepackage.Avatar;
import compositepackage.OzScene;

public class Assignment9 {
	public static void main(String[] args) {

		int x1 = 0;
		int x2 = 100;
		int x3 = 200;
		int y = 100;

		ImageInterface abackground = new ABackground("Background.jpg", 0, 0);
		Avatar aDorothy = new AnAvatar(x1, y,
				new AnImage("Dorothy.jpg", x1, y), new ARotatingLine(x1 + 40,
						y + 80, 40, Math.PI / 2), new AnAngle(x1 + 40, y + 80,
						new ARotatingLine(x1, y, 50, Math.PI / 4),
						new ARotatingLine(x1, y, 50, (Math.PI / 4)
								+ (Math.PI / 2))), new AnAngle(x1 + 40,
						x1 + 220, new ARotatingLine(x1, y, 50, Math.PI / 4),
						new ARotatingLine(x1, y, 50, (Math.PI / 4)
								+ (Math.PI / 2))));
		Avatar aScarecrow = new AnAvatar(x2, y, new AnImage("ScareCrow.jpg",
				x2, y), new ARotatingLine(x2 + 40, y + 80, 40, Math.PI / 2),
				new AnAngle(x2 + 40, y + 80, new ARotatingLine(x2, y, 50,
						Math.PI / 4), new ARotatingLine(x2, y, 50,
						(Math.PI / 4) + (Math.PI / 2))), new AnAngle(x2 + 40,
						x2 + 120, new ARotatingLine(x2, y, 50, Math.PI / 4),
						new ARotatingLine(x2, y, 50, (Math.PI / 4)
								+ (Math.PI / 2))));
		Avatar aOz = new AnAvatar(x3, y, new AnImage("OZ.jpg", x3, y),
				new ARotatingLine(x3 + 40, y + 80, 40, Math.PI / 2),
				new AnAngle(x3 + 40, y + 80, new ARotatingLine(x3, y, 50,
						Math.PI / 4), new ARotatingLine(x3, y, 50,
						(Math.PI / 4) + (Math.PI / 2))), new AnAngle(x3 + 40,
						x3 + 20, new ARotatingLine(x3, y, 50, Math.PI / 4),
						new ARotatingLine(x3, y, 50, (Math.PI / 4)
								+ (Math.PI / 2))));

		OzScene ozscene = new AOzScene(abackground, aDorothy, aScarecrow, aOz);
		AScanner scanner = new AScanner("");
		CommandInterpreter aCommandInterpreter = new ACommandInterpreter(
				ozscene, scanner);

		JFrame frame = new JFrame("Assignment 9");
		// JButton button = new JButton("Process");
		JTextField commandField = new JTextField();

		frame.setLayout(new GridLayout(2, 1));
		frame.add(commandField);
		// frame.add(button);
		frame.setSize(250, 150);
		frame.setVisible(true);

		AController aController = new AController(aCommandInterpreter,
				commandField);
		aController.setModel(aCommandInterpreter);
		
		AView view = new AView(ozscene);
		JFrame anotherframe = new JFrame("View");
		anotherframe.add(view);
		anotherframe.setSize(700,700);
		anotherframe.setVisible(true);

//		String[] commands = new String[3];
//		commands[0] = "Say Dorothy \"Hello\"";
//		commands[1] = "Move Oz 200 200";
//		commands[2] = "Say ScareCrow \"Thank you!\"";
//
//		for (int i = 0; i < commands.length; i++) {
//			aCommandInterpreter.setcommand(commands[i]);
//			ThreadSupport.sleep(2000);
//		}

	}
}