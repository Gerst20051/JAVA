package main;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

import objectpackage.ABackground;
import objectpackage.AJTextField;
import objectpackage.ARotatingLine;
import objectpackage.ATextField;
import objectpackage.AnImage;
import objectpackage.ImageInterface;

import scannerpackage.AScanner;
import util.misc.ThreadSupport;

import commandpackage.ACommandInterpreter;
import commandpackage.AController;
import commandpackage.ARoundedTextField;
import commandpackage.CommandInterpreter;
import commandpackage.TextComponentInterface;
import compositepackage.AKeyController;
import compositepackage.AMouseController;
import compositepackage.AOzScene;
import compositepackage.AView;
import compositepackage.AnAngle;
import compositepackage.AnAvatar;
import compositepackage.Avatar;
import compositepackage.OzScene;

public class Assignment10 {
	public static void main(String[] args) {

TextComponentInterface textfield = new ATextField();
TextComponentInterface jtextfield = new AJTextField();
//		TextComponentInterface roundedtextfield = new ARoundedTextField();		
//
		OzScene ozscene = new AOzScene();
		AScanner scanner = new AScanner("");
		CommandInterpreter aCommandInterpreter = new ACommandInterpreter(
				ozscene, scanner);
		OEFrame oeFrame1 = ObjectEditor.edit(ozscene);
		oeFrame1.hideMainPanel();
		oeFrame1.setLocation(0, 0);
		oeFrame1.setSize(400, 400);
		ThreadSupport.sleep(1000);
		OEFrame oeFrame2 = ObjectEditor.edit(aCommandInterpreter);
		System.out.println("About to animate");
		aCommandInterpreter.animateDorothy();
		ThreadSupport.sleep(3000);
		aCommandInterpreter.animateOz();
		aCommandInterpreter.animateScarecrow();
//
//		JFrame frame = new JFrame("Assignment 10");
//		frame.setLayout(new GridLayout(2, 1));
//		frame.add((Component) jtextfield);
//		frame.setSize(250, 150);
//		frame.setVisible(true);
//
		AController aController = new AController(aCommandInterpreter,
				jtextfield);
		// aController.setModel(aCommandInterpreter);
		//
		AController secondController = new AController(aCommandInterpreter,
				textfield);
		aController.setModel(aCommandInterpreter);
		// //
		// // AController thirdController = new AController(aCommandInterpreter,
		// // roundedtextfield);
		// // aController.setModel(aCommandInterpreter);
		//
		//
		// JFrame anotherframe = new JFrame("AView");
		// PropertyChangeListener view = new AView(ozscene);
		// anotherframe.add((Component) view);
		// anotherframe.setSize(500,500);
		// anotherframe.setVisible(true);
		//
		JFrame secondframe = new JFrame("TextField");
		secondframe.add((Component) textfield);
		secondframe.setSize(250, 150);
		secondframe.setVisible(true);
		// //
		// //
		// // JFrame thirdframe = new JFrame("RoundedTextField");
		// // thirdframe.add((Component) roundedtextfield);
		// // thirdframe.setSize(250,150);
		// // thirdframe.setVisible(true);
		//		
//		ObjectEditor.edit(ozscene);
//		ObjectEditor.edit(aCommandInterpreter);

//		MouseListener mousecontroller = new AMouseController(ozscene,(AView) view); 
//		KeyListener keycontroller = new AKeyController(ozscene, (AView) view);


	}
}