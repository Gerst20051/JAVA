/*
 * @(#)GradAnim.java	1.5	98/12/03
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
import java.awt.image.*;
import javax.swing.*;


/**
 * The GradAnim class demonstrates GradientPaint animation.
 */
public class GradAnim extends JApplet implements Runnable {

    private static final int MAX_HUE = 256 * 6;
    private animval x1, y1, x2, y2;

    private int hue = (int) (Math.random() * MAX_HUE);
    private Thread thread;
    private BufferedImage bimg;


    public void init() {
        setBackground(Color.white);

        // initializes starting and ending coordinates for the gradient
        x1 = new animval(0, 300, 2, 10);
        y1 = new animval(0, 300, 2, 10);
        x2 = new animval(0, 300, 2, 10);
        y2 = new animval(0, 300, 2, 10);
    }


    // resets the limits of the starting and ending coordinates
    public void reset(int w, int h) {
        x1.newlimits(0, w);
        y1.newlimits(0, h);
        x2.newlimits(0, w);
        y2.newlimits(0, h);
    }


    // calls anim() to generate new coordinates and generates a new hue
    public void step(int w, int h) {
        x1.anim(); y1.anim();
        x2.anim(); y2.anim();
        hue = (hue + (int) (Math.random() * 10)) % MAX_HUE;
    }


    // generates a new color with the specified hue
    public static Color getColor(int hue) {
        int leg = (hue / 256) % 6;
        int step = (hue % 256) * 2;
        int falling = (step < 256) ? 255 : 511 - step;
        int rising = (step < 256) ? step : 255;
        int r, g, b;
        r = g = b = 0;
        switch (leg) {
        case 0:         // red
            r = 255;  
            break;
        case 1:        // reddish-green
            r = falling;
            g = rising;
            break;
        case 2:        // green
            g = 255;    
            break;
        case 3:        // greenish-blue
            g = falling;
            b = rising;
            break;
        case 4:       // blue
            b = 255;
            break;
        case 5:       // blueish-red
            b = falling;
            r = rising;
            break;
        }
        return new Color(r, g, b);
    }


    // renders the gradient and the yellow line
    public void drawDemo(int w, int h, Graphics2D g2) {

        Color c1 = getColor(hue);
        Color c2 = getColor(hue + 256 * 3);
        GradientPaint gp = new GradientPaint(x1.getFlt(), y1.getFlt(), c1,
                                         x2.getFlt(), y2.getFlt(), c2,
                                         true);
        g2.setPaint(gp);
        g2.fillRect(0, 0, w, h);
        g2.setColor(Color.yellow);
        g2.drawLine(x1.getInt(), y1.getInt(), x2.getInt(), y2.getInt());
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
                thread.sleep(10);
            } catch (InterruptedException e) { break; }
        }
        thread = null;
    }



    /**
     * The animval class generates new coordinate values used
     * to set the starting and ending points of the gradient.
     */
    public class animval {
        float curval;
        float lowval;
        float highval;
        float currate;
        float lowrate;
        float highrate;

        public animval(int lowval, int highval,
                       int lowrate, int highrate) {
            this.lowval = lowval;
            this.highval = highval;
            this.lowrate = lowrate;
            this.highrate = highrate;

            // the current coordinate value
            this.curval = randval(lowval, highval);

            // used as increment for curval
            this.currate = randval(lowrate, highrate);
        }

        public float randval(float low, float high) {
            return (float) (low + Math.random() * (high - low));
        }

        public float getFlt() {
            return curval;
        }

        public int getInt() {
            return (int) curval;
        }


        // called by x1, y1, x2, y2 to obtain new coordinate values
        public void anim() {
            curval += currate;
            clip();
        }
	

        // resets curval and currate
        public void clip() {
            if (curval > highval) {
                curval = highval - (curval - highval);
                if (curval < lowval) {
                    curval = highval;
                }
                currate = - randval(lowrate, highrate);
            } else if (curval < lowval) {
                curval = lowval + (lowval - curval);
                if (curval > highval) {
                    curval = lowval;
                }
                currate = randval(lowrate, highrate);
            }
        }

       
        // resets the coordinate limits
        public void newlimits(int lowval, int highval) {
            this.lowval = lowval;
            this.highval = highval;
            clip();
        }
    }


    public static void main(String argv[]) {
        final GradAnim demo = new GradAnim();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - GradAnim");
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
