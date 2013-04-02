/*
 * @(#)AllFonts.java	1.7 98/12/03
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
package Fonts;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;


/**
 * The AllFonts class displays a scrolling text of fonts returned from 
 * GraphicsEnvironment.getAllFonts().
 */
public class AllFonts extends JApplet {

    Demo demo;

    public void init() {
        getContentPane().add(demo = new Demo());
        getContentPane().add("North", new DemoControls(demo));
    }

    public void start() {
        demo.start();
    }
  
    public void stop() {
        demo.stop();
    }



    /**
     * The Demo class performs the scrolling and rendering.
     */
    static class Demo extends JPanel implements Runnable {
    
        private static Vector fonts = new Vector();

        /* 
         * gets the available fonts from the GraphicsEnviroment
         * and puts them into a Vector
         */
        static {
            GraphicsEnvironment ge = 
                GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font allfonts[] = ge.getAllFonts();
            for (int i = 0; i < allfonts.length; i++) {
                if (allfonts[i].canDisplayUpTo(allfonts[i].getName()) > 0) {
                    fonts.addElement(allfonts[i]);
                }
            }
        }

        // the number of strings
        private int nStrs;

        // the height of a string
        private int strH;

        // used to index into the Vector fonts
        private int fi;
        private Thread thread;
        private BufferedImage bimg;
        private Vector v = new Vector();

        public long sleepAmt = 500;
        public int fsize = 14;
    
    
        public Demo() {
            setBackground(Color.white);
        }
    

        /* 
         * Compute how many strings can fit into the new height.
         */
        public void reset(int w, int h) {
            v.clear();
            Font f = ((Font) fonts.get(0)).deriveFont(Font.PLAIN,fsize);
            FontMetrics fm = getFontMetrics(f);
            strH = (int) (fm.getAscent()+fm.getDescent());
            nStrs = h/strH + 1;
            fi = 0;
        }

    
        /*
         * copies one Font from the fonts Vector to the end of the v
         * Vector until the end of the fonts Vector is reached.  Once
         * the v Vector is filled with strings equaling the height of
         * the surface, starts removing the first element of the Vector
         * v.  Once the end of fonts Vector is reached, continues
         * to remove elements from the v Vector until the end of the
         * v Vector is reached.  When the end of v Vector is reached, 
         * begins process again.
         */
        public void step(int w, int h) {
            if (fi < fonts.size()) {
                v.addElement(((Font) fonts.get(fi)).deriveFont(Font.PLAIN,fsize));
            }
            if (v.size() == nStrs && v.size() != 0 || fi > fonts.size()) {
                v.removeElementAt(0);
            }
            fi = (v.size() == 0) ? 0 : ++fi;
        }

    
        // renders all of the Vector v's elements.
        public void drawDemo(int w, int h, Graphics2D g2) {
    
            g2.setColor(Color.black);
    
            int yy = (fi >= fonts.size()) ? 0 : h - v.size() * strH - strH/2;
    
            for (int i = 0; i < v.size(); i++) {
                Font f = (Font) v.get(i);
                int sw = getFontMetrics(f).stringWidth(f.getName());
                g2.setFont(f);
                g2.drawString(f.getName(), (int) (w/2-sw/2),yy += strH);
            }
        }
    
    
        public Graphics2D createGraphics2D(int w, int h) {
            Graphics2D g2 = null;
            if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
                bimg = (BufferedImage) createImage(w, h);
                reset(w, h);
            } 
            g2 = bimg.createGraphics();
            g2.setBackground(getBackground());
            g2.clearRect(0, 0, w, h);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            return g2;
        }
    
    
        public void paint(Graphics g) {
            Dimension d = getSize();
            step(d.width, d.height);
            Graphics2D g2 = createGraphics2D(d.width, d.height);
            drawDemo(d.width, d.height, g2);
            g2.dispose();
            g.drawImage(bimg, 0, 0, this);
        }
    
    
        public void start() {
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        }
    
    
        public synchronized void stop() {
            thread = null;
        }
    
    
        public void run() {
            Thread me = Thread.currentThread();
            while (thread == me) {
                repaint();
                try {
                    thread.sleep(sleepAmt);
                } catch (InterruptedException e) { break; }
            }
            thread = null;
        }
    } // End Demo class



    /**
     * The DemoControls class provides controls for selecting font size and
     * sleep amount.
     */
    static class DemoControls extends JPanel implements ActionListener, ChangeListener {

        Demo demo;
        JSlider slider;
        int fsize[] = { 8, 14, 18, 24, 32 };
        JMenuItem menuitem[] = new JMenuItem[fsize.length];
        Font font[] = new Font[fsize.length];
        JLabel label;


        public DemoControls(Demo demo) {
            this.demo = demo;
            setBackground(Color.gray);

            label = new JLabel(String.valueOf(demo.sleepAmt) + "  ms");
            label.setForeground(Color.black);
            add(label);
            slider = new JSlider(JSlider.HORIZONTAL, 100, 999, (int) demo.sleepAmt);
            slider.setBorder(new EtchedBorder());
            slider.setPreferredSize(new Dimension(90,22));
            slider.addChangeListener(this);
            add(slider);
            JMenuBar menubar = new JMenuBar();
            add(menubar);
            JMenu menu = (JMenu) menubar.add(new JMenu("Font Size"));
            menu.setIcon(new DownArrowIcon());
            menu.setFont(new Font("serif", Font.PLAIN, 12));

            for (int i = 0; i < fsize.length; i++) {
                font[i] = new Font("serif", Font.PLAIN, fsize[i]);
                menuitem[i] = menu.add(new JMenuItem(String.valueOf(fsize[i])));
                menuitem[i].setFont(font[i]);
                menuitem[i].addActionListener(this);
            }
        }


        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < fsize.length; i++) {
                if (e.getSource().equals(menuitem[i])) {
                    demo.fsize = fsize[i];
                    Dimension d = demo.getSize();
                    demo.reset(d.width, d.height);
                    break;
                }
            }
        }


        public void stateChanged(ChangeEvent e) {
            demo.sleepAmt = (long) slider.getValue();
            label.setText(String.valueOf(demo.sleepAmt) + " ms");
            label.repaint();
        }



        /**
         * The DownArrowIcon class renders an icon for the control
         * menus that looks like an arrow pointing down.
         */
        static class DownArrowIcon implements Icon {
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g;
                GeneralPath p1 = new GeneralPath();
                p1.moveTo(10,10);
                p1.lineTo(14,18);
                p1.lineTo(18,10);
                p1.closePath();
                g2.setColor(Color.black);
                g2.fill(p1);
            }
            public int getIconWidth() { return 20; }
            public int getIconHeight() { return 20; }
        } // End DownArrowIcon class

    } // End DemoControls class


    public static void main(String argv[]) {
        final AllFonts demo = new AllFonts();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - AllFonts");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
            public void windowDeiconified(WindowEvent e) { demo.start(); }
            public void windowIconified(WindowEvent e) { demo.stop(); }
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
        demo.start();
    }
}
