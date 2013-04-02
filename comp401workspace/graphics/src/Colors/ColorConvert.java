/*
 * @(#)ColorConvert.java	1.6  98/12/03
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
package Colors;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import javax.swing.*;
import java.net.URL;


/**
 * The ColorConvert class demonstrates a ColorConvertOp operation that
 * converts a ColorSpace.TYPE_RGB BufferedImage to a ColorSpace.CS_GRAY 
 * BufferedImage.
 */
public class ColorConvert extends JApplet {

    private static Image img;
    private static Color colors[] = { Color.red, Color.pink, Color.orange, 
         Color.yellow, Color.green, Color.magenta, Color.cyan, Color.blue};


    public void init() {
        setBackground(Color.white);
        URL url = ColorConvert.class.getResource("clouds.jpg");
        img = getToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {}
    }


    public void drawDemo(int w, int h, Graphics2D g2) {

        int iw = img.getWidth(this);
        int ih = img.getHeight(this);

        FontRenderContext frc = g2.getFontRenderContext();
        Font font = g2.getFont();
        g2.setColor(Color.black);
        TextLayout tl = new TextLayout("ColorConvertOp RGB->GRAY", font, frc);

        /*
         * centers the string "ColorConvertOp RGB->GRAY" at the top
         */ 
        tl.draw(g2, (float) (w/2-tl.getBounds().getWidth()/2), 
                            tl.getAscent()+tl.getLeading());

        BufferedImage srcImg = 
            new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
        Graphics2D srcG = srcImg.createGraphics();
        RenderingHints rhs = g2.getRenderingHints();
        srcG.setRenderingHints(rhs);
        srcG.drawImage(img, 0, 0, null);
        String s = "JavaColor";
        Font f = new Font("serif", Font.BOLD, iw/6);
        tl = new TextLayout(s, f, frc);
        Rectangle2D tlb = tl.getBounds();

        // puts the string "JavaColor" into an array of chars
        char[] chars = s.toCharArray();
        float charWidth = 0.0f;
        int rw = iw/chars.length;
        int rh = ih/chars.length;

        /* 
         * for each char in chars[], creates a TextLayout and
         * renders the text with a different color from the colors array.
         * Fills colored rectangles above and below. 
         */
        for (int i = 0; i < chars.length; i++) {
            tl = new TextLayout(String.valueOf(chars[i]), f, frc);
            Shape shape = tl.getOutline(null);
            srcG.setColor(colors[i%colors.length]);
            tl.draw(srcG, (float) (iw/2-tlb.getWidth()/2+charWidth), 
                        (float) (ih/2+tlb.getHeight()/2));
            charWidth += (float) shape.getBounds().getWidth();
            srcG.fillRect(i*rw, ih-rh, rw, rh);
            srcG.setColor(colors[colors.length-1-i%colors.length]);
            srcG.fillRect(i*rw, 0, rw, rh);
        }

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp theOp = new ColorConvertOp(cs, rhs);
        BufferedImage dstImg = 
                new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
        theOp.filter(srcImg, dstImg);

        g2.drawImage(srcImg, 10, 20, w/2-20, h-30, null);
        g2.drawImage(dstImg, w/2+10, 20, w/2-20, h-30, null);
    }



    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
	Dimension d = getSize();
        g2.setBackground(getBackground());
        g2.clearRect(0, 0, d.width, d.height);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);
        drawDemo(d.width, d.height, g2);
    }


    public static void main(String argv[]) {
        final ColorConvert demo = new ColorConvert();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - ColorConvert"); 
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add(demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
