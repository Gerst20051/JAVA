package assignment9;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class Main {
	public static void main (String[] args) {
		Canvas canvas = new Canvas();
		AScanner scanner = new AScanner("");
		AParser parser = new AParser(canvas, scanner);
		TextComponentInterface textfield = new ATextField();
		TextComponentInterface jtextfield = new AJTextField();
		TextComponentInterface roundedtextfield = new ARoundedTextField();

		JFrame frame = new JFrame("Assignment 9");
		frame.setLayout(new GridLayout(2, 1));
		frame.add((Component) jtextfield);
		frame.setSize(250, 150);
		frame.setVisible(true);

		AController firstController = new AController(parser, jtextfield);
		firstController.setModel(parser);
		AController secondController = new AController(parser, textfield);
		secondController.setModel(parser);
		AController thirdController = new AController(parser, roundedtextfield);
		thirdController.setModel(parser);
		
		JFrame firstframe = new JFrame("AView");
		PropertyChangeListener view = new AView(canvas);
		firstframe.add((Component) view);
		firstframe.setSize(650, 695);
		firstframe.setVisible(true);
		
		JFrame secondframe = new JFrame("TextField");
		secondframe.add((Component) textfield);
		secondframe.setSize(250, 150);
		secondframe.setVisible(true);
		
		JFrame thirdframe = new JFrame("RoundedTextField");
		thirdframe.add((Component) roundedtextfield);
		thirdframe.setSize(250, 150);
		thirdframe.setVisible(true);
		
		MouseListener mouseController = new AMouseController(canvas, (AView) view);
		KeyListener keyController = new AKeyController(canvas, (AView) view);
		
		OEFrame CanvasOE = ObjectEditor.edit(canvas);
		CanvasOE.hideMainPanel();
		CanvasOE.setSize(650, 695);
		ObjectEditor.edit(parser);
		canvas.init();
		canvas.animate();
	}
}