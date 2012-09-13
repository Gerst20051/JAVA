/*
 * @(#)Tree.java	1.5 98/12/03
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
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;


/**
 * The Tree class displays keyboard characters in a 'tree' design by 
 * performing transformations on the characters.
 */
public class Tree extends JApplet implements Runnable {

    private char theC = 'A';
    private Character theT = new Character(theC);
    private Character theR = new Character((char) ((int) theC + 1));
    private Thread thread;
    private BufferedImage bimg;


    public void init() {
        setBackground(Color.white);
    }


    /*
     * increments theT and theR to the next Character and theC to the next
     * char
     */
    public void step(int w, int h) {
        theT = new Character(theC = ((char) ((int) theC + 1)));
        theR = new Character((char) ((int) theC + 1));
        if (theR.compareTo(new Character('z')) == 0) {
            theC = 'A';
        }
    }


    public void drawDemo(int w, int h, Graphics2D g2) {
        int mindim = Math.min(w, h);
        AffineTransform at = new AffineTransform();
        at.translate((w - mindim) / 2.0,
                     (h - mindim) / 2.0);
        at.scale(mindim, mindim);
        at.translate(0.5, 0.5);
        at.scale(0.3, 0.3);
        at.translate(-(Twidth + Rwidth), FontHeight / 4.0);
        g2.transform(at);
        tree(g2, mindim * 0.3, 0);

    }


    static Font theFont = new Font("serif", Font.PLAIN, 1);
    static double Twidth = 0.6;
    static double Rwidth = 0.6;
    static double FontHeight = 0.75;
    static Color colors[] = {Color.blue,
                             Color.red.darker(),
                             Color.green.darker()};


    // sets the colors, transforms and draws the characters
    public void tree(Graphics2D g2d, double size, int phase) {
        g2d.setColor(colors[phase % 3]);
        new TextLayout(theT.toString(),theFont,g2d.getFontRenderContext()).draw(g2d, 0.0f, 0.0f);
        if (size > 10.0) {
            AffineTransform at = new AffineTransform();
            at.setToTranslation(Twidth, -0.1);
            at.scale(0.6, 0.6);
            g2d.transform(at);
            size *= 0.6;
            new TextLayout(theR.toString(),theFont, g2d.getFontRenderContext()).draw(g2d, 0.0f, 0.0f);
            at.setToTranslation(Rwidth+0.75, 0);
            g2d.transform(at);
            Graphics2D g2dt = (Graphics2D) g2d.create();
            at.setToRotation(-Math.PI / 2.0);
            g2dt.transform(at);
            tree(g2dt, size, phase + 1);
            g2dt.dispose();
            at.setToTranslation(.75, 0);
            at.rotate(-Math.PI / 2.0);
            at.scale(-1.0, 1.0);
            at.translate(-Twidth, 0);
            g2d.transform(at);
            tree(g2d, size, phase);
        }
        g2d.setTransform(new AffineTransform());
    }


    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
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
        try {
            thread.sleep(3333);
        } catch (InterruptedException e) { return; }
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(3333);
            } catch (InterruptedException e) { break; }
        }
        thread = null;
    }


    public static void main(String argv[]) {
        final Tree demo = new Tree();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - Tree");
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
