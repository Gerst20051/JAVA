/*
 * @(#)FillStroke.java	1.4 98/12/03
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
package Paths;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.awt.geom.GeneralPath;
import javax.swing.*;


/**
 * The FillStroke class demonstrates basic implementation of GeneralPath, 
 * by filling and stroking a path without closing it.
 */
public class FillStroke extends JApplet {


    public void init() {
        setBackground(Color.white);
    }


    public void drawDemo(int w, int h, Graphics2D g2) {
        GeneralPath p = new GeneralPath(GeneralPath.WIND_EVEN_ODD);

        /*
         * creates a simple GeneralPath triangle without closing
         * the path
         */
        p.moveTo( w*.5f, h*.15f);
        p.lineTo( w*.8f, h*.75f);
        p.lineTo( w*.2f, h*.75f);
        g2.setColor(Color.lightGray);

        // fills the entire triangle
        g2.fill(p);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(10));

        // the first two lines are rendered without closing the path
        g2.draw(p);

        // renders the description string in the center beneath the triangle
        TextLayout tl = new TextLayout("Fill, Stroke w/o closePath", 
                                g2.getFont(), g2.getFontRenderContext());
        tl.draw(g2, (float)(w/2-tl.getBounds().getWidth()/2), h*.85f);
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
        final FillStroke demo = new FillStroke();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - FillStroke");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
