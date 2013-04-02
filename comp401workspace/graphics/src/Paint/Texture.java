/*
 * @(#)Texture.java	1.5 98/12/03
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
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import javax.swing.*;;


/**
 * The Texture class demonstrates creating TexturePaint objects with shapes, 
 * gradients and rgb values.
 */
public class Texture extends JApplet {

    private static TexturePaint bluedots, greendots, triangles;
    private static TexturePaint blacklines, gradient;

    // creates the TexturePaint objects 
    static {
        BufferedImage bi = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        Graphics2D gi = bi.createGraphics();
        gi.setBackground(Color.white);
        gi.clearRect(0,0,10,10);

        // creates the triangles TexturePaint
        GeneralPath p1 = new GeneralPath();
        p1.moveTo(0,0);
        p1.lineTo(5,10);
        p1.lineTo(10,0);
        p1.closePath();
        gi.setColor(Color.lightGray);
        gi.fill(p1);
        triangles = new TexturePaint(bi,new Rectangle(0,0,10,10));

        // creates the blacklines TexturePaint
        bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
        gi = bi.createGraphics();
        gi.setColor(Color.black);
        gi.fillRect(0,0,5,5);
        gi.setColor(Color.gray);
        gi.fillRect(1,1,4,4);
        blacklines = new TexturePaint(bi,new Rectangle(0,0,5,5));

        // creates the gradient TexturePaint
        int w = 30; int h = 30;
        bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        gi = bi.createGraphics();
        Color oc = Color.white; Color ic = Color.lightGray;
        gi.setPaint(new GradientPaint(0,0,oc,w*.35f,h*.35f,ic));
        gi.fillRect(0, 0, w/2, h/2);
        gi.setPaint(new GradientPaint(w,0,oc,w*.65f,h*.35f,ic));
        gi.fillRect(w/2, 0, w/2, h/2);
        gi.setPaint(new GradientPaint(0,h,oc,w*.35f,h*.65f,ic));
        gi.fillRect(0, h/2, w/2, h/2);
        gi.setPaint(new GradientPaint(w,h,oc,w*.65f,h*.65f,ic));
        gi.fillRect(w/2, h/2, w/2, h/2);
        gradient = new TexturePaint(bi,new Rectangle(0,0,w,h));

        // creates the bluedots TexturePaint
        bi = new BufferedImage(2,2,BufferedImage.TYPE_INT_RGB);
        bi.setRGB(0, 0, 0xffffffff); bi.setRGB(1, 0, 0xffffffff);
        bi.setRGB(0, 1, 0xffffffff); bi.setRGB(1, 1, 0xff0000ff);
        bluedots = new TexturePaint(bi,new Rectangle(0,0,2,2));

        // creates the greendots TexturePaint
        bi = new BufferedImage(2,2,BufferedImage.TYPE_INT_RGB);
        bi.setRGB(0, 0, 0xffffffff); bi.setRGB(1, 0, 0xffffffff);
        bi.setRGB(0, 1, 0xffffffff); bi.setRGB(1, 1, 0xff00ff00);
        greendots = new TexturePaint(bi,new Rectangle(0,0,2,2));
    }


    public void init() {
        setBackground(Color.white);
    }


    public void drawDemo(int w, int h, Graphics2D g2) {

        Rectangle r = new Rectangle(10,10,w-20,h/2-20);

        g2.setPaint(gradient);
        g2.fill(r);

        g2.setPaint(Color.green);
        g2.setStroke(new BasicStroke(20));
        g2.draw(r);

        g2.setPaint(blacklines);
        g2.setStroke(new BasicStroke(15));
        g2.draw(r);

        Font f = new Font("Times New Roman", Font.BOLD, w/5);
        TextLayout tl = new TextLayout("Texture", f, g2.getFontRenderContext());
        int sw = (int) tl.getBounds().getWidth();
        int sh = (int) tl.getBounds().getHeight();
        Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(w/2-sw/2, h*.25+sh/2));

        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(3));
        g2.draw(sha);

        g2.setPaint(greendots);
        g2.fill(sha);

        // resets location of rectangle and fills with triangles Texture
        r.setLocation(10,h/2+10);
        g2.setPaint(triangles);
        g2.fill(r);

        g2.setPaint(blacklines);
        g2.setStroke(new BasicStroke(20));
        g2.draw(r);

        g2.setPaint(Color.green);
        g2.setStroke(new BasicStroke(4));
        g2.draw(r);

        f = new Font("serif", Font.BOLD, w/4);
        tl = new TextLayout("Paint", f, g2.getFontRenderContext());
        sw = (int) tl.getBounds().getWidth();
        sh = (int) tl.getBounds().getHeight();
        sha = tl.getOutline(AffineTransform.getTranslateInstance(w/2-sw/2, h*.75+sh/2));

        // strokes outline shape of "Paint" with black
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
        g2.draw(sha);

        // fills outline shape of "Paint" with bluedots Texture
        g2.setPaint(bluedots);
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
        final Texture demo = new Texture();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - Texture");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
