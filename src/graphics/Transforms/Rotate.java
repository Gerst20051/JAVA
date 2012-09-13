/*
 * @(#)Rotate.java	1.5 98/12/03
 *
 * Copyright 1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.AffineTransform;
import javax.swing.*;


/**
 * The Rotate class renders rotated ellipses and includes controls for 
 * choosing the increment and emphasis.  Emphasis is defined as which 
 * ellipses have a darker color and thicker stroke.
 */
public class Rotate extends JApplet {


    public void init() {
        Demo demo = new Demo();
        getContentPane().add(demo);
        getContentPane().add("North", new DemoControls(demo));
    }



    /**
     * The Demo class performs the rotation and painting
     */
    public class Demo extends Canvas {
    
        public double increment = 5.0;
        public int emphasis = 9;
    
        public Demo() {
            setBackground(Color.white);
        }
    
    
        public void drawDemo(int w, int h, Graphics2D g2) {
            int size = Math.min(w, h);
            float ew = size/4;
            float eh = size-20;
            Ellipse2D ellipse = new Ellipse2D.Float(-ew/2, -eh/2, ew, eh);

            /* 
             * from 0 to 360 degrees, increases the degrees by the
             * selected increment amount and renders the transformed ellipses
             */
            for (double angdeg = 0; angdeg < 360; angdeg+=increment) {

	        // set stroke to a wider stroke every emphasis degrees
                if (angdeg % emphasis == 0) {
                    g2.setColor(Color.gray);
                    g2.setStroke(new BasicStroke(2.0f));
                } else {
                    g2.setColor(Color.lightGray);
                    g2.setStroke(new BasicStroke(0.5f));
                }
                AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);
                at.rotate(Math.toRadians(angdeg));
                g2.draw(at.createTransformedShape(ellipse));
            }

            // renders the blue and yellow ellipses in the center
            g2.setColor(Color.blue);
            ellipse.setFrame(w/2-10,h/2-10,20,20);
            g2.fill(ellipse);
            g2.setColor(Color.gray);
            g2.setStroke(new BasicStroke(6));
            g2.draw(ellipse);
            g2.setColor(Color.yellow);
            g2.setStroke(new BasicStroke(4));
            g2.draw(ellipse);
            g2.setColor(Color.black);
            g2.drawString("Rotate", 5, 15);
        }
    
    
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Dimension d = getSize();
            g2.setBackground(getBackground());
            g2.clearRect(0, 0, d.width, d.height);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            drawDemo(d.width, d.height, g2);
        }
    } // End Demo class



    /**
     * The DemoControls class adds textfields for specifying increment
     * and emphasis.
     */
    static class DemoControls extends JPanel implements ActionListener {

        Demo demo;
        JTextField tf1, tf2;

        public DemoControls(Demo demo) {
            this.demo = demo;
            setBackground(Color.gray);
            JLabel l = new JLabel("Increment:");
            l.setForeground(Color.black);
            add(l);
            add(tf1 = new JTextField("5.0"));
            tf1.setPreferredSize(new Dimension(30,24));
            tf1.addActionListener(this);
            add(l = new JLabel("  Emphasis:"));
            l.setForeground(Color.black);
            add(tf2 = new JTextField("9"));
            tf2.setPreferredSize(new Dimension(30,24));
            tf2.addActionListener(this);
        }


        public void actionPerformed(ActionEvent e) {
            try { 
                if (e.getSource().equals(tf1)) {
                    demo.increment = Double.parseDouble(tf1.getText().trim());
                    if (demo.increment < 1.0) {
                        demo.increment = 1.0;
                    }
                } else {
                    demo.emphasis = Integer.parseInt(tf2.getText().trim());
                }
                demo.repaint();
            } catch (Exception ex) {}
        }
    } // End DemoControls class


    public static void main(String s[]) {
        Rotate demo = new Rotate();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - Rotate");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
} // End Rotate class
