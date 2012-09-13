/*
 * @(#)Stars3D.java	1.3 98/12/03
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
import java.awt.geom.*;
import java.awt.font.FontRenderContext;
import javax.swing.*;


/**
 * The Stars3D class generates a 3D text shape with GeneralPath, renders a 
 * number of small multi-colored rectangles and then renders the 
 * 3D text shape.
 */
public class Stars3D extends JApplet {


    public void init() {
        Demo demo = new Demo();
        getContentPane().add(demo);
        getContentPane().add("North", new DemoControls(demo));
    }



    static class Demo extends JPanel {

        // the colors of the stars    
        private static Color colors[] = { Color.red, Color.green, Color.white };
        private static AffineTransform at = AffineTransform.getTranslateInstance(-5, -5);

        private Shape shape, tshape;
        private Shape ribbon;
        public int fontSize = 100;
        public String text = "J2D";
        public int numStars = 300;
    
    
        public Demo() {
            setBackground(Color.black);
        }
    
    
        public void drawDemo(int w, int h, Graphics2D g2) {
    
            Rectangle2D rect = new Rectangle2D.Double();

            /*
             * sets the color of each star, randomly sets their location
             * and renders them
             */
            for (int i = 0; i < numStars; i++) {
                g2.setColor(colors[i%3]);
                g2.setComposite(AlphaComposite.getInstance(
                             AlphaComposite.SRC_OVER, (float) Math.random()));
                rect.setRect(w*Math.random(), h*Math.random(),2,2);
                g2.fill(rect);
            }
    
            FontRenderContext frc = g2.getFontRenderContext();
            Font font = new Font("serif.bolditalic", Font.PLAIN, fontSize);

            /*
             * gets the outline shape of the glyph vector created
             * from the current font and String
             */
            shape = font.createGlyphVector(frc, text).getOutline();

            // creates a shape transformed with the current transform
            tshape = at.createTransformedShape(shape);

            // gets a PathIterator that iterates the boundary of shape
            PathIterator pi = shape.getPathIterator(null);
            
            float seg[] = new float[6];
            float tseg[] = new float[6];
            
            GeneralPath working = new GeneralPath(GeneralPath.WIND_NON_ZERO);
            float x=0, y=0; // Current point on the path
            float tx=0, ty=0; // Transformed path point
            float cx=0, cy=0; // Last moveTo point, for SEG_CLOSE
            float tcx=0, tcy=0; // Transformed last moveTo point
            
            /*
             * iterates through the Shape and builds the ribbon
             * by adding GeneralPath objects
             */
            while(!pi.isDone()) {
                int segType = pi.currentSegment(seg);
                switch(segType) {
                    case PathIterator.SEG_MOVETO:
                            at.transform(seg, 0, tseg, 0, 1);
                            x = seg[0];
                            y = seg[1];
                            tx = tseg[0];
                            ty = tseg[1];
                            cx = x;
                            cy = y;
                            tcx = tx;
                            tcy = ty;
                            break;
                    case PathIterator.SEG_LINETO:
                            at.transform(seg, 0, tseg, 0, 1);
                            if (Line2D.relativeCCW(x, y, tx, ty,
                                                   seg[0], seg[1]) < 0) {
                                working.moveTo(x, y);
                                working.lineTo(seg[0], seg[1]);
                                working.lineTo(tseg[0], tseg[1]);
                                working.lineTo(tx, ty);
                                working.lineTo(x, y);
                            } else {
                                working.moveTo(x, y);
                                working.lineTo(tx, ty);
                                working.lineTo(tseg[0], tseg[1]);
                                working.lineTo(seg[0], seg[1]);
                                working.lineTo(x, y);
                            }
                            
                            x = seg[0];
                            y = seg[1];
                            tx = tseg[0];
                            ty = tseg[1];
                            break;
                            
                    case PathIterator.SEG_QUADTO:
                            at.transform(seg, 0, tseg, 0, 2);
                            if (Line2D.relativeCCW(x, y, tx, ty,
                                                   seg[2], seg[3]) < 0) {
                                working.moveTo(x, y);
                                working.quadTo(seg[0], seg[1],
                                               seg[2], seg[3]);
                                working.lineTo(tseg[2], tseg[3]);
                                working.quadTo(tseg[0], tseg[1],
                                               tx, ty);
                                working.lineTo(x, y);
                            } else {
                                working.moveTo(x, y);
                                working.lineTo(tx, ty);
                                working.quadTo(tseg[0], tseg[1],
                                               tseg[2], tseg[3]);
                                working.lineTo(seg[2], seg[3]);
                                working.quadTo(seg[0], seg[1],
                                               x, y);
                            }
                    
                            x = seg[2];
                            y = seg[3];
                            tx = tseg[2];
                            ty = tseg[3];
                            break;
            
                    case PathIterator.SEG_CUBICTO:
                            at.transform(seg, 0, tseg, 0, 3);
                            if (Line2D.relativeCCW(x, y, tx, ty,
                                                   seg[4], seg[5]) < 0) {
                                working.moveTo(x, y);
                                working.curveTo(seg[0], seg[1],
                                                seg[2], seg[3],
                                                seg[4], seg[5]);
                                working.lineTo(tseg[4], tseg[5]);
                                working.curveTo(tseg[2], tseg[3],
                                                tseg[0], tseg[1],
                                                tx, ty);
                                working.lineTo(x, y);
                            } else {
                                working.moveTo(x, y);
                                working.lineTo(tx, ty);
                                working.curveTo(tseg[0], tseg[1],
                                                tseg[2], tseg[3],
                                                tseg[4], tseg[5]);
                                working.lineTo(seg[4], seg[5]);
                                working.curveTo(seg[2], seg[3],
                                                seg[0], seg[1],
                                                x, y);
                            }
                    
                            x = seg[4];
                            y = seg[5];
                            tx = tseg[4];
                            ty = tseg[5];
                            break;
            
                    case PathIterator.SEG_CLOSE:
                            if (Line2D.relativeCCW(x, y, tx, ty,
                                                   cx, cy) < 0) {
                                working.moveTo(x, y);
                                working.lineTo(cx, cy);
                                working.lineTo(tcx, tcy);
                                working.lineTo(tx, ty);
                                working.lineTo(x, y);
                            } else {
                                working.moveTo(x, y);
                                working.lineTo(tx, ty);
                                working.lineTo(tcx, tcy);
                                working.lineTo(cx, cy);
                                working.lineTo(x, y);
                            }
                            x = cx; 
                            y = cy;
                            tx = tcx;
                            ty = tcy;
                }
                pi.next();
            } // while
            ribbon = working;
    
            g2.setComposite(AlphaComposite.SrcOver);
            Rectangle r = shape.getBounds();
            g2.translate(w*.5-r.width*.5,h*.5+r.height*.5);

            // fills the transformed shape with blue
            g2.setColor(Color.blue);
            g2.fill(tshape);
	
            /* 
             * fills the shape producing the 3D effect with a 
             * partially opaque white color
             */
            g2.setColor(new Color(255, 255, 255, 200));
            g2.fill(ribbon);
	 
            // fills the untransformed shape with white
            g2.setColor(Color.white);
            g2.fill(shape);

            // strokes the outline of shape with blue    
            g2.setColor(Color.blue);
            g2.draw(shape);
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
    }  // End Demo class



    /**
     * The DemoControls class adds controls for entering a string
     * to be rendered and for entering the size of that string.
     */ 
    static class DemoControls extends JPanel implements ActionListener {

        Demo demo;
        JTextField tf1, tf2;

        public DemoControls(Demo demo) {
            this.demo = demo;
            setBackground(Color.gray);
            JLabel l = new JLabel("  Text:");
            l.setForeground(Color.black);
            add(l);
            add(tf1 = new JTextField(demo.text));
            tf1.setPreferredSize(new Dimension(60,20));
            tf1.addActionListener(this);
            l = new JLabel("  Size:");
            l.setForeground(Color.black);
            add(l);
            add(tf2 = new JTextField(String.valueOf(demo.fontSize)));
            tf2.setPreferredSize(new Dimension(30,20));
            tf2.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            try { 
                if (e.getSource().equals(tf1)) {
                    demo.text = tf1.getText().trim();
                } else if (e.getSource().equals(tf2)) {
                    demo.fontSize = Integer.parseInt(tf2.getText().trim());
                    if (demo.fontSize < 10) {
                        demo.fontSize = 10;
                    }
                }
                demo.repaint();
            } catch (Exception ex) {}
        }
    } // End DemoControls class



    public static void main(String argv[]) {
        final Stars3D demo = new Stars3D();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - Stars3D");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
