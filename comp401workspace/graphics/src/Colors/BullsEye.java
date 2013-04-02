/*
 * @(#)BullsEye.java	1.5  98/12/03
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
import java.awt.geom.Ellipse2D;
import javax.swing.*;


/**
 * The BullsEye class renders Ellipses with colors set by alpha values.
 */
public class BullsEye extends JApplet {


    public void init() {
        setBackground(Color.white);
    }


    public void drawDemo(int w, int h, Graphics2D g2) {

        Color reds[] = { Color.red.darker(), Color.red };
     
        /*
         * fills 18 Ellipse2D.Float objects, which get smaller as 
         * N increases
         */
        for (int N = 0; N < 18; N++) {
            float i = (N + 2) / 2.0f;
            float x = (float) (5+i*(w/2/10));
            float y = (float) (5+i*(h/2/10));
            float ew = (w-10)-(i*w/10);
            float eh = (h-10)-(i*h/10);

            /*
             * assigns a higher value of alpha, corresponding to
             * a higher value of N
             */
            float alpha = (N == 0) ? 0.1f : 1.0f / (19.0f - N);

            // sets the ellipse to a darker version of red if N < 16
            if ( N >= 16 ) {
                g2.setColor(reds[N-16]);
            } else {
                g2.setColor(new Color(0f, 0f, 0f, alpha));
            }
            g2.fill(new Ellipse2D.Float(x,y,ew,eh));
        }
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
        final BullsEye demo = new BullsEye();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - BullsEye");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
