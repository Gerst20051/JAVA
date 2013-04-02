/*
 * @(#)ACrules.java	1.4 98/12/03
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
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import javax.swing.*;


/**
 * The ACrules class demonstrates all of the AlphaCompositing rules. 
 */
public class ACrules extends JApplet implements Runnable {


    // the names of the compositing rules 
    private static String compNames[] = {
        "(Source)",
        "Src",
        "SrcOver",
        "SrcIn",
        "SrcOut",
        "(Dest)",
        "Clear",
        "DstOver",
        "DstIn",
        "DstOut"
    };


    // the compositing rules 
    private static AlphaComposite compObjs[] = {
        AlphaComposite.Src,
        AlphaComposite.Src,
        AlphaComposite.SrcOver,
        AlphaComposite.SrcIn,
        AlphaComposite.SrcOut,
        AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f),
        AlphaComposite.Clear,
        AlphaComposite.DstOver,
        AlphaComposite.DstIn,
        AlphaComposite.DstOut
    };

    private int fadeIndex;


    // alpha values 
    private static float fadeValues[][] = {
        { 1.0f,-0.1f, 0.0f, 1.0f, 0.0f, 1.0f},
        { 0.0f, 0.1f, 1.0f, 1.0f,-0.1f, 0.0f},
        { 1.0f, 0.0f, 1.0f, 0.0f, 0.1f, 1.0f},
    };
    private static String fadeNames[] = {
        "Src => transparent, Dest opaque",
        "Src => opaque, Dest => transparent",
        "Src opaque, Dest => opaque",
    };
    private static Font f = new Font("serif", Font.PLAIN, 10);
    private float srca = fadeValues[fadeIndex][0];
    private float dsta = fadeValues[fadeIndex][3];
    private String fadeLabel = fadeNames[0];
    private BufferedImage statBI, animBI;
    private int PADLEFT, PADRIGHT, HPAD;
    private int PADABOVE, PADBELOW, VPAD;
    private int RECTWIDTH, RECTHEIGHT;
    private int PADDEDWIDTH, PADDEDHEIGHT;
    private GeneralPath srcpath = new GeneralPath();
    private GeneralPath dstpath = new GeneralPath();
    private LineMetrics lm;
    private BufferedImage dBI, sBI;
    private GradientPaint gradientDst, gradientSrc;
    private Thread thread;
    private BufferedImage bimg;
    private long sleepAmount = 400;


    public void init() {
        setBackground(Color.white);
    }


    public void reset(int w, int h) {
        sleepAmount = 400;
        FontRenderContext frc = new FontRenderContext(null, false, false);
        lm = f.getLineMetrics(compNames[0], frc);

	// sets the size of the rectangles
        PADLEFT  = (w < 150) ? 10 : 15;
        PADRIGHT = (w < 150) ? 10 : 15;
        HPAD     = (PADLEFT + PADRIGHT);
        PADABOVE = 2 + (int) lm.getHeight();
        PADBELOW = 2;
        VPAD     = (PADABOVE + PADBELOW);
        RECTWIDTH = w/4 - HPAD;
        RECTWIDTH = (RECTWIDTH < 6) ? 6 : RECTWIDTH;
        RECTHEIGHT = (h-VPAD)/5 - VPAD;
        RECTHEIGHT = (RECTHEIGHT < 6) ? 6 : RECTHEIGHT;
        PADDEDWIDTH  = (RECTWIDTH  + HPAD);
        PADDEDHEIGHT = (RECTHEIGHT + VPAD);
        
        // defines the path to contain the blue source 
        srcpath.reset();
        srcpath.moveTo(0,0);
        srcpath.lineTo(RECTWIDTH,0);
        srcpath.lineTo(RECTWIDTH,RECTHEIGHT/2);
        srcpath.lineTo(0,RECTHEIGHT);
        srcpath.closePath();
        
        // defines the path to contain the red destination
        dstpath.reset();
        dstpath.moveTo(0,0);
        dstpath.lineTo(0,RECTHEIGHT/2);
        dstpath.lineTo(RECTWIDTH,RECTHEIGHT);
        dstpath.lineTo(RECTWIDTH,0);
        dstpath.closePath();
        
        // creates a BufferedImage to contain the destination path 
        dBI = new BufferedImage(RECTWIDTH, RECTHEIGHT,
                                    BufferedImage.TYPE_INT_ARGB);

        // creates a BufferedImage to contain the source path
        sBI = new BufferedImage(RECTWIDTH, RECTHEIGHT,
                                    BufferedImage.TYPE_INT_ARGB);

        // creates a red opaque to red transparent gradient
        gradientDst = new GradientPaint(0, 0,
                                      new Color(1.0f, 0.0f, 0.0f, 1.0f),
                                      0, RECTHEIGHT,
                                      new Color(1.0f, 0.0f, 0.0f, 0.0f));

        // creates a blue opaque to blue transparent gradient
        gradientSrc = new GradientPaint(0, 0,
                                      new Color(0.0f, 0.0f, 1.0f, 1.0f),
                                      RECTWIDTH, 0,
                                      new Color(0.0f, 0.0f, 1.0f, 0.0f));

        // creates a BufferedImage for the left side of the window
        statBI = new BufferedImage(w/2, h, BufferedImage.TYPE_INT_RGB);
  
        // draws the left side of the window
        statBI = drawCompBI(statBI, true);

        // creates a BufferedImage for the right side of the window
        animBI = new BufferedImage(w/2, h, BufferedImage.TYPE_INT_RGB);
    }


    // sets the source and destination alpha values
    public void step(int w, int h) {
        if (sleepAmount == 5000) {
            sleepAmount = 400;
        }

        srca = srca + fadeValues[fadeIndex][1];
        dsta = dsta + fadeValues[fadeIndex][4];
        fadeLabel = fadeNames[fadeIndex];
        if (srca < 0 || srca > 1.0 || dsta < 0 || dsta > 1.0) {
            sleepAmount = 5000;
            srca = fadeValues[fadeIndex][2];
            dsta = fadeValues[fadeIndex][5];
            if (fadeIndex++ == fadeValues.length-1) {
                fadeIndex = 0;
            }
        }
    }


    public void drawDemo(int w, int h, Graphics2D g2) {

        if (statBI == null || animBI == null) {
            return;
        }
        g2.drawImage(statBI, 0, 0, null);
        g2.drawImage(drawCompBI(animBI, false), w/2, 0, null);

        g2.setColor(Color.black);
        FontRenderContext frc = g2.getFontRenderContext();
        TextLayout tl = new TextLayout("AlphaComposite Rules", g2.getFont(), frc);
        tl.draw(g2, 15.0f, (float) tl.getBounds().getHeight()+3.0f);

        tl = new TextLayout(fadeLabel, g2.getFont(), frc);
        float x = (float) (w*0.75-tl.getBounds().getWidth()/2);
        if ((x + tl.getBounds().getWidth()) > w) {
            x = (float) (w - tl.getBounds().getWidth());
        }
        tl.draw(g2, x, (float) tl.getBounds().getHeight()+3.0f);
    }


    // draws into the BufferedImage objects, sBI and dBI 
    private BufferedImage drawCompBI(BufferedImage bi, boolean doGradient)
    {
        Graphics2D big = bi.createGraphics();
        big.setColor(getBackground());
        big.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        big.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        big.setFont(f);

        Graphics2D gD = dBI.createGraphics();
        gD.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        Graphics2D gS = sBI.createGraphics();
        gS.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 0, y = 0;
        int yy = (int) lm.getHeight() + VPAD;

        for (int i = 0; i < compNames.length; i++) {
            y = (i == 0 || i == 5) ? yy : y + PADDEDHEIGHT;
            x = (i >= 5) ? bi.getWidth()/2+PADLEFT : PADLEFT;
            big.translate(x, y);

            gD.setComposite(AlphaComposite.Clear);
            gD.fillRect(0, 0, RECTWIDTH, RECTHEIGHT);
            gD.setComposite(AlphaComposite.Src);
            if (doGradient) {
                gD.setPaint(gradientDst);
                gD.fillRect(0, 0, RECTWIDTH, RECTHEIGHT);
            } else {
                gD.setPaint(new Color(1.0f, 0.0f, 0.0f, dsta));
                gD.fill(dstpath);
            }

            gS.setComposite(AlphaComposite.Clear);
            gS.fillRect(0, 0, RECTWIDTH, RECTHEIGHT);
            gS.setComposite(AlphaComposite.Src);
            if (doGradient) {
                gS.setPaint(gradientSrc);
                gS.fillRect(0, 0, RECTWIDTH, RECTHEIGHT);
            } else {
                gS.setPaint(new Color(0.0f, 0.0f, 1.0f, srca));
                gS.fill(srcpath);
            }

            gD.setComposite(compObjs[i]);
            gD.drawImage(sBI, 0, 0, null);

            big.drawImage(dBI, 0, 0, null);
            big.setColor(Color.black);
            big.drawString(compNames[i], 0, -lm.getDescent());
            big.drawRect(0, 0, RECTWIDTH, RECTHEIGHT);
            big.translate(-x, -y);
        }

        gD.dispose();
        gS.dispose();
        big.dispose();

        return bi;
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
                thread.sleep(sleepAmount);
            } catch (InterruptedException e) { break; }
        }
        thread = null;
    }


    public static void main(String argv[]) {
        final ACrules demo = new ACrules();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - ACrules");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
            public void windowDeiconified(WindowEvent e) { demo.start(); }
            public void windowIconified(WindowEvent e) { demo.stop(); }
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(500,350));
        f.show();
        demo.start();
    }
}
