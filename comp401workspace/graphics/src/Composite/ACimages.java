/*
 * @(#)ACimages.java	1.6  98/12/03
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
package Composite;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import javax.swing.*;
import java.net.URL;


/**
 * The ACimages class demonstrates compositing shapes over images.
 */
public class ACimages extends JApplet {

    private static String s[] = { "box", "fight", "magnify",
                        "boxwave", "globe", "snooze",
                        "tip", "thumbsup", "dukeplug"};
    private static Image imgs[] = new Image[s.length];
    private static Color colors[] = { Color.blue, Color.cyan, Color.green,
                        Color.magenta, Color.orange, Color.pink,
                        Color.red, Color.yellow, Color.lightGray };


    public void init() {
        setBackground(Color.white);
        Toolkit tk = getToolkit();
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = tk.getImage(ACimages.class.getResource(s[i] + ".gif"));
            try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(imgs[i], 0);
                tracker.waitForID(0);
            } catch (Exception e) {}
        }
    }


    public void drawDemo(int w, int h, Graphics2D g2) {

        float alpha = 0.0f;
        int iw = w/3;
        int ih = (h-45)/3;
        float xx = 0, yy = 15;

        for (int i =0; i < imgs.length; i++) {

           /* 
            * arranges the images so that they are displayed three across
            * and three down
            */
            xx = (i%3 == 0) ? 0 : xx+w/3;
            switch (i) {
                case 3 : yy = h/3+15; break;
                case 6 : yy = h/3*2+15;
            }

            // set the compositing rule to SRC_OVER for the drawString call
            g2.setComposite(AlphaComposite.SrcOver);
            g2.setColor(Color.black);

            // creates a new AlphaComposite and adds .1 to its alpha value
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha += .1f);

            // draws the String that displays the alpha value
            String s = "a=" + Float.toString(alpha).substring(0,3);
            g2.drawString(s, (int) xx+3, (int) yy-2);

            Shape shape=null;

            switch (i%3) {
                case 0 : shape = new Ellipse2D.Float(xx, yy, iw, ih);
                        break;
                case 1 : shape = new RoundRectangle2D.Float(xx, yy, iw, ih, 25, 25);
                        break;
                case 2 : shape = new Rectangle2D.Float(xx, yy, iw, ih);
                        break;
            }

            g2.setColor(colors[i]);
            g2.setComposite(ac); 
            g2.fill(shape);
            g2.drawImage(imgs[i], (int) xx, (int) yy, iw, ih, null);
        }
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
        final ACimages demo = new ACimages();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - ACimages");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
