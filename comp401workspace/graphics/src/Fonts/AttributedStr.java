/*
 * @(#)AttributedStr.java	1.1	98/12/03
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
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator;
import javax.swing.JFrame;
import javax.swing.JApplet;


/**
 * The AttributedString class demonstrates how to build an AttributedString 
 * and render the string broken over lines. 
 */
public class AttributedStr extends JApplet {

    static Color black = new Color(20, 20, 20); 
    static Color blue = new Color(94, 105, 176); 
    static Color yellow = new Color(255, 255, 140);
    static Color red = new Color(149, 43, 42);
    static Color white = new Color(240, 240, 255); 
    static String text = "  A quick brown  fox  jumped  over the lazy duke  ";

    // creates an AttributedString to hold text and attribute information  
    static AttributedString as = new AttributedString(text);
    static AttributedCharacterIterator aci; 

    static {

        // creates the bullet and replaces the first character
        Shape shape = new Ellipse2D.Double(0,25,12,12);
	ShapeGraphicAttribute sga = new ShapeGraphicAttribute(shape, GraphicAttribute.TOP_ALIGNMENT, false);
	as.addAttribute(TextAttribute.CHAR_REPLACEMENT, sga, 0, 1);

        // adds font and color attributes for 'A'
        Font font = new Font("serif", Font.PLAIN, 50);
        int index = text.indexOf("A")+1;;
        as.addAttribute(TextAttribute.FONT, font, 0, index);
        as.addAttribute(TextAttribute.FOREGROUND, white, 0, index);

        // adds font attribute for 'quick'
        font = new Font("sanserif", Font.BOLD | Font.ITALIC, 20);
        index = text.indexOf("quick");
        as.addAttribute(TextAttribute.FONT, font, index, index+5);

        // adds font and color attributes for 'brown'
        index = text.indexOf("brown");
        font = new Font("serif", Font.BOLD, 20);
        as.addAttribute(TextAttribute.FONT, font, index, index+5);
        as.addAttribute(TextAttribute.FOREGROUND, red, index, index+5);

        // adds font attributes for each character in 'fox'
        index = text.indexOf("fox");
        AffineTransform fontAT = new AffineTransform();
        fontAT.rotate(Math.toRadians(10));
        Font fx = new Font("serif", Font.BOLD, 30).deriveFont(fontAT);
        as.addAttribute(TextAttribute.FONT, fx, index, index+1);
        as.addAttribute(TextAttribute.FONT, fx, index+1, index+2);
        as.addAttribute(TextAttribute.FONT, fx, index+2, index+3);

        // adds font attribute for 'jumped'
        fontAT.setToRotation(Math.toRadians(-4));
        fx = font.deriveFont(fontAT);
        index = text.indexOf("jumped");
        as.addAttribute(TextAttribute.FONT, fx, index, index+6);

        // adds font and color attributes for 'over'
        font = new Font("serif", Font.BOLD | Font.ITALIC, 30);
        index = text.indexOf("over");
        as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, index, index+4);
        as.addAttribute(TextAttribute.FOREGROUND, white, index, index+4);
        as.addAttribute(TextAttribute.FONT, font, index, text.length());

        // adds font attribute for 'duke'
        font = new Font("dialog", Font.PLAIN, 20);
        int i = text.indexOf("duke");
        as.addAttribute(TextAttribute.FONT, font, index, i-1);

        // creates TexturePaint to appear behind 'duke'
        BufferedImage bi = new BufferedImage(4,4,BufferedImage.TYPE_INT_ARGB);
        bi.setRGB(0, 0, 0xffffffff); 
        TexturePaint tp = new TexturePaint(bi,new Rectangle(0,0,4,4));
        as.addAttribute(TextAttribute.BACKGROUND, tp, i, i+4);
        font = new Font("serif", Font.BOLD, 40);
        as.addAttribute(TextAttribute.FONT, font, i, i+4);
    }


    public void init() {
        setBackground(Color.white);
        Font font = new Font("dialog", Font.PLAIN, 40);
        int size = getFontMetrics(font).getHeight();
        BufferedImage bimg = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D big = bimg.createGraphics();
        Image img = getToolkit().getImage(AttributedStr.class.getResource("snooze.gif"));
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {}

        // draws duke image to BufferedImage
        big.drawImage(img, 0, 0, size, size, null);

	// creates ImageGraphicAttribute with BufferedImage of duke
	ImageGraphicAttribute iga = new ImageGraphicAttribute(bimg, GraphicAttribute.TOP_ALIGNMENT);
	as.addAttribute(TextAttribute.CHAR_REPLACEMENT, iga, text.length()-1, text.length());

        // obtain the AttributedCharacterIterator from the AttributedString
        aci = as.getIterator();
    }


    public void paint(Graphics g) {
        Dimension d = getSize();
        int w = d.width;
        int h = d.height;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // fills background with blue to black gradient
        g2.setPaint(new GradientPaint(0,h,blue,w,0,black));
        g2.fillRect(0, 0, w, h);

        // creates LineBreakMeasurer to be used to wrap the text
        float x = 5, y = 0;
        FontRenderContext frc = g2.getFontRenderContext();
        LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);

        // writes "AttributedString LineBreakMeasurer" at the top
        g2.setColor(white);
        String s = "AttributedString LineBreakMeasurer";
        Font font = new Font("serif", Font.PLAIN, 12);
        TextLayout tl = new TextLayout(s, font, frc);
        
        tl.draw(g2, 5, y += (float) tl.getBounds().getHeight());

        g2.setColor(yellow);

        /*
         * enumerates through the TextLayout objects in lbm, renders
         * each TextLayout object and stores its height in y, 
         * which is used as the y coordinate.  If the bottom
         * of the surface area has not been reached after running
         * out of TextLayout objects from lbm, resets lbm and 
         * continues to render TextLayout objects until the
         * bottom of the surface area is reached.
         */
        while (y < h-tl.getAscent()) {
            lbm.setPosition(0);
            while (lbm.getPosition() < text.length()) {
                tl = lbm.nextLayout(w-x);
                if (!tl.isLeftToRight()) {
                    x = w - tl.getAdvance();
                }
                tl.draw(g2, x, y += tl.getAscent());
                y += tl.getDescent() + tl.getLeading();
            }
        }
    }


    public static void main(String s[]) {
        final AttributedStr demo = new AttributedStr();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - AttributedStr");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
