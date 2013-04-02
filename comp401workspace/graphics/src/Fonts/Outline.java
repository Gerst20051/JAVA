/*
 * @(#)Outline.java	1.7	98/12/03
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
import java.awt.geom.AffineTransform;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator;
import javax.swing.*;


/**
 * The Outline class renders text as outline shapes.
 */
public class Outline extends JApplet {

    public void init() {
        setBackground(Color.white);
    }


    public void drawDemo(int w, int h, Graphics2D g2) {

        FontRenderContext frc = g2.getFontRenderContext();
        Font f = new Font("sansserif",Font.PLAIN,w/8);
        Font f1 = new Font("sansserif",Font.ITALIC,w/8);
        String s = "AttributedString";
        AttributedString as = new AttributedString(s);
   
        /*
         * applies the TextAttribute.Font attribute to the AttributedString 
         * with the range 0 to 10, which encompasses the letters 'A' through
         * 'd' of the String "AttributedString"
         */ 
        as.addAttribute(TextAttribute.FONT, f, 0, 10 );

        /*
         * applies the TextAttribute.Font attribute to the AttributedString 
         * with the range 10 to the length of the String s, which encompasses
         * the letters 'S' through 'g' of String "AttributedString"
         */ 
        as.addAttribute(TextAttribute.FONT, f1, 10, s.length() );

        AttributedCharacterIterator aci = as.getIterator();

        // creates a TextLayout from the AttributedCharacterIterator
        TextLayout tl = new TextLayout (aci, frc);
        float sw = (float) tl.getBounds().getWidth();
        float sh = (float) tl.getBounds().getHeight();

        /*
         * creates an outline shape from the TextLayout and centers it
         * with respect to the width of the surface
         */
        Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(w/2-sw/2, h*0.2+sh/2));
        g2.setColor(Color.blue);
        g2.setStroke(new BasicStroke(1.5f));
        g2.draw(sha);
        g2.setColor(Color.magenta);
        g2.fill(sha);


        // creates a TextLayout from the String "Outline"
        f = new Font("serif", Font.BOLD,w/6);
        tl = new TextLayout("Outline", f, frc);
        sw = (float) tl.getBounds().getWidth();
        sh = (float) tl.getBounds().getHeight();
        sha = tl.getOutline(AffineTransform.getTranslateInstance(w/2-sw/2,h*0.5+sh/2));
        g2.setColor(Color.black);
        g2.draw(sha);
        g2.setColor(Color.red);
        g2.fill(sha);


        f = new Font("sansserif",Font.ITALIC,w/8);
 
        /*
         * creates a new shearing AffineTransform 
         */ 
        AffineTransform fontAT = new AffineTransform();
        fontAT.shear(-0.2, 0.0);

        // applies the fontAT transform to Font f
        Font derivedFont = f.deriveFont(fontAT);

        /*
         * creates a TextLayout from the String "Italic-Shear" and with
         * the transformed Font object
         */
        tl = new TextLayout("Italic-Shear", derivedFont, frc);
        sw = (float) tl.getBounds().getWidth();
        sh = (float) tl.getBounds().getHeight();
        sha = tl.getOutline(AffineTransform.getTranslateInstance(w/2-sw/2,h*0.80f+sh/2));
        g2.setColor(Color.green);
        g2.draw(sha);
        g2.setColor(Color.black);
        g2.fill(sha);
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


    public static void main(String argv[]) {
        final Outline demo = new Outline();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - Outline");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
