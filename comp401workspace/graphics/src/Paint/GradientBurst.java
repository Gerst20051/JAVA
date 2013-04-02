/*
 * @(#)GradientBurst.java	1.3 98/12/03
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
package Paint;

import java.awt.*;
import java.awt.font.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;


/**
 * The GradientBurst class illustrates GradientPaint in a burst effect and 
 * includes controls for choosing different colors.
 */
public class GradientBurst extends JApplet {

    public void init() {
        Demo demo = new Demo();
        getContentPane().add(demo);
        getContentPane().add("North", new DemoControls(demo));
    }



    /**
     * The Demo class performs the painting.
     */
    public class Demo extends JPanel {
    
        public Color innerColor, outerColor;
    
        public Demo() {
            setBackground(Color.white);
            innerColor = Color.green;
            outerColor = Color.blue;
        }
    
    
        public void drawDemo(int w, int h, Graphics2D g2) {
            
	    /* 
             * a rectangle in which to render the gradient in the upper
             * left quarter of the window
             */
            Rectangle2D rect1 = new Rectangle2D.Float(0.0f, 0.0f, w/2, h/2);

            /*
             * fills the gradient paint starting with the outer color
             * at the upper left corner and ending with the inner color
             * near the lower right corner of rect1
             */ 
            GradientPaint gp = 
                new GradientPaint(0,0,outerColor,w*.35f,h*.35f,innerColor);
            g2.setPaint(gp);
            g2.fill(rect1);

	    /* 
             * a rectangle in which to render the gradient in the upper
             * right quarter of the window
             */    
            rect1 = new Rectangle2D.Float(w/2, 0f, w/2, h/2);

            /*
             * fills the gradient paint starting with the outer color
             * at the upper right corner and ending with the inner color
             * near the lower left corner of rect1
             */ 
            gp = new GradientPaint(w,0,outerColor,w*.65f,h*.35f,innerColor);
            g2.setPaint(gp);
            g2.fill(rect1);

	    /* 
             * a rectangle in which to render the gradient in the lower
             * left quarter of the window
             */        
            rect1 = new Rectangle2D.Float(0f, h/2, w/2, h/2);

            /*
             * fills the gradient paint starting with the outer color
             * at the lower left corner and ending with the inner color
             * near the upper right corner of rect1
             */ 
            gp = new GradientPaint(0,h,outerColor,w*.35f,h*.65f,innerColor);
            g2.setPaint(gp);
            g2.fill(rect1);

	    /* 
             * a rectangle in which to render the gradient in the lower
             * right quarter of the window
             */      
            rect1 = new Rectangle2D.Float(w/2, h/2, w/2, h/2);

            /*
             * fills the gradient paint starting with the outer color
             * at the lower right corner and ending with the inner color
             * near the upper left corner of rect1
             */ 
            gp = new GradientPaint(w,h,outerColor,w*.65f,h*.65f,innerColor);
            g2.setPaint(gp);
            g2.fill(rect1);
    
            // renders "GradientPaint" in the center of the window
            g2.setColor(Color.black);
            TextLayout tl = new TextLayout(
                    "GradientPaint", g2.getFont(), g2.getFontRenderContext());
            tl.draw(g2, (int) (w/2-tl.getBounds().getWidth()/2),
                    (int) (h/2+tl.getBounds().getHeight()/2));
        }
    
    
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Dimension d = getSize();
            g2.setBackground(getBackground());
            g2.clearRect(0, 0, d.width, d.height);
            drawDemo(d.width, d.height, g2);
        }
    }  // End Demo class



    /** 
     * The DemoControls class provides controls for selecting 
     * gradient colors
     */
    static class DemoControls extends JPanel implements ActionListener {

        Demo demo;
        Color colors[] = 
                { Color.red, Color.orange, Color.yellow, Color.green,
                  Color.blue, Color.lightGray, Color.cyan, Color.magenta };
        String colorName[] =
                { "Red", "Orange", "Yellow", "Green", 
                  "Blue", "lightGray", "Cyan", "Magenta" };
        
        JMenuItem innerMI[] = new JMenuItem[colors.length];
        JMenuItem outerMI[] = new JMenuItem[colors.length];
        ColoredSquare squares[] = new ColoredSquare[colors.length];
        JMenu imenu, omenu;

        public DemoControls(Demo demo) {
            this.demo = demo;
            setBackground(Color.gray);
            JMenuBar inMenuBar = new JMenuBar();
            add(inMenuBar);
            JMenuBar outMenuBar = new JMenuBar();
            add(outMenuBar);
            Font font = new Font("serif", Font.PLAIN, 10);

            imenu = (JMenu) inMenuBar.add(new JMenu("Inner Color"));
            imenu.setFont(font);
            imenu.setIcon(new ColoredSquare(demo.innerColor));
            omenu = (JMenu) outMenuBar.add(new JMenu("Outer Color"));
            omenu.setFont(font);
            omenu.setIcon(new ColoredSquare(demo.outerColor));
            for (int i = 0; i < colors.length; i++) {
                squares[i] = new ColoredSquare(colors[i]);
                innerMI[i] = imenu.add(new JMenuItem(colorName[i]));
                innerMI[i].setFont(font);
                innerMI[i].setIcon(squares[i]);
                innerMI[i].addActionListener(this);
                outerMI[i] = omenu.add(new JMenuItem(colorName[i]));
                outerMI[i].setFont(font);
                outerMI[i].setIcon(squares[i]);
                outerMI[i].addActionListener(this);
            } 
        }


        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < colors.length; i++) {
                if (e.getSource().equals(innerMI[i])) {
                    demo.innerColor = colors[i];
                    imenu.setIcon(squares[i]);
                    break;
                } else if (e.getSource().equals(outerMI[i])) {
                    demo.outerColor = colors[i];
                    omenu.setIcon(squares[i]);
                    break;
                }
            }
            demo.repaint();
        }



        /**
         * The ColoredSquare class provides little colored icons
         * for the control menus
         */
        class ColoredSquare implements Icon {
            Color color;
            public ColoredSquare(Color c) {
                this.color = c;
            }
    
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Color oldColor = g.getColor();
                g.setColor(color);
                g.fill3DRect(x,y,getIconWidth(), getIconHeight(), true);
                g.setColor(oldColor);
            }
            public int getIconWidth() { return 12; }
            public int getIconHeight() { return 12; }
        } // End ColoredSquare class


    } // End DemoControls class


    public static void main(String argv[]) {
        final GradientBurst demo = new GradientBurst();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - GradientBurst");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
