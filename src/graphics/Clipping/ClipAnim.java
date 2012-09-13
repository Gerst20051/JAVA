/*
 * @(#)ClipAnim.java	1.6 98/12/03
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
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;


/**
 * The ClipAnim class demonstrates animated clipping of different objects
 * and controls for multiple clips.
 */
public class ClipAnim extends JApplet {

    Demo demo;

    public void init() {
        getContentPane().add(demo = new Demo());
        getContentPane().add("North", new DemoControls(demo));
    }

    public void start() {
        demo.start();
    }
  
    public void stop() {
        demo.stop();
    }



    /**
     * The Demo class performs the animation and painting and loads
     * the images.
     */
    static class Demo extends JPanel implements Runnable {

        static Image dimg, cimg;
        static Color redBlend = new Color(255, 0, 0, 120);
        static Color greenBlend = new Color(0, 255, 0, 120);
        static BasicStroke bs = new BasicStroke(20.0f);
        static TexturePaint texture;

        // creates the TexturePaint pattern
        static {
            BufferedImage bi = new BufferedImage(5, 5, BufferedImage.TYPE_INT_RGB);
            Graphics2D big = bi.createGraphics();
            big.setBackground(Color.yellow);
            big.clearRect(0,0,5,5);
            big.setColor(Color.red);
            big.fillRect(0,0,3,3);
            texture = new TexturePaint(bi,new Rectangle(0,0,5,5));
        }
        private AnimVal animval[] = new AnimVal[3]; 
        protected boolean doObjects = true;  
        private Font originalFont = new Font("serif", Font.PLAIN, 12);
        private Font font;
        private GradientPaint gradient;
        private int strX, strY;
        private int dukeX, dukeY;
        private Thread thread;
        private BufferedImage bimg;
    
    
        public Demo() {
            cimg = getImage("clouds.jpg");
            dimg = getImage("duke.gif");
            setBackground(Color.white);
            animval[0] = new AnimVal(true);
            animval[1] = new AnimVal(false);
            animval[2] = new AnimVal(false);
        }
    
    
        public Image getImage(String name) {
            URL url = ClipAnim.class.getResource(name);
            Image img = getToolkit().getImage(url);
            try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                tracker.waitForID(0);
            } catch (Exception e) {}
            return img;
        }
    
    
        public void reset(int w, int h) {
            for (int i = 0; i < animval.length; i++) {
                animval[i].reset(w, h);
            }
            gradient = new GradientPaint(0,h/2,Color.red,w*.4f,h*.9f,Color.yellow);
            dukeX = (int) (w*.25-dimg.getWidth(this)/2);
            dukeY = (int) (h*.25-dimg.getHeight(this)/2);
            FontMetrics fm = getFontMetrics(originalFont);
            double sw = fm.stringWidth("CLIPPING");
            double sh = fm.getAscent()+fm.getDescent();
            double sx = (w/2-30)/sw;
            double sy = (h/2-30)/sh;
            AffineTransform Tx = AffineTransform.getScaleInstance(sx, sy);
            font = originalFont.deriveFont(Tx);
            fm = getFontMetrics(font);
            strX = (int) (w*.75 - fm.stringWidth("CLIPPING")/2);
            strY = (int) (h*.72 + fm.getAscent()/2);
        }
    
    
        public void step(int w, int h) {
            for (int i = 0; i < animval.length; i++) {
                if (animval[i].isSelected) {
                    animval[i].step(w, h);
                }
            }
        }
    
    
        public void drawDemo(int w, int h, Graphics2D g2) {
    
            GeneralPath p1 = new GeneralPath();
            GeneralPath p2 = new GeneralPath();
    
            // for each Clip selected, appends shapes to clipping path 
            for (int i = 0; i < animval.length; i++) {
                if (animval[i].isSelected) {
                    double x = animval[i].x; double y = animval[i].y;
                    double ew = animval[i].ew; double eh = animval[i].eh;
                    p1.append(new Ellipse2D.Double(x, y, ew, eh), false);
                    p2.append(new Rectangle2D.Double(x+5, y+5, ew-10, eh-10),false);
                }
            }
            if (animval[0].isSelected || animval[1].isSelected || 
                animval[2].isSelected) 
            {
	        // sets clip to intersection of p1 and current clip 
                g2.setClip(p1);

                // sets clip to p2
                g2.clip(p2);
            }
    
            if (doObjects) {
                // renders images, string and painted rects
                int w2 = w/2;
                int h2 = h/2;
                g2.drawImage(cimg, 0, 0, w2, h2, null);
                g2.drawImage(dimg, dukeX, dukeY, null);
    
                g2.setPaint(texture);
                g2.fillRect(w2, 0, w2, h2);
    
                g2.setPaint(gradient);
                g2.fillRect(0, h2, w2, h2);
    
                g2.setColor(Color.lightGray);
                g2.fillRect(w2, h2, w2, h2);
                g2.setColor(Color.red);
                g2.drawOval(w2, h2, w2-1, h2-1);
                g2.setFont(font);
                g2.drawString("CLIPPING", strX, strY);
            } else {
                g2.setColor(Color.lightGray);
                g2.fillRect(0, 0, w, h);
            }
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
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                                RenderingHints.VALUE_RENDER_QUALITY);
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
                    thread.sleep(10);
                } catch (InterruptedException e) { break; }
            }
            thread = null;
        }
    
    
        /**
         * The AnimVal class generates new coordinate and size values.
         */ 
        public class AnimVal {
            double ix = 5.0;
            double iy = 3.0;
            double iw = 5.0;
            double ih = 3.0;
            double x, y;
            double ew, eh;   // ellipse width & height
            boolean isSelected;
    
            public AnimVal(boolean isSelected) {
                this.isSelected = isSelected;
            }
    

            /*
             * advances the x and y coordinates with the incrementer.
             * increases or decreases the ellipse width and height.
             * bounce the shape off the walls.
             */
            public void step(int w, int h) {
                x += ix;
                y += iy;
                ew += iw;
                eh += ih;
                if (ew > w/2) {
                    ew = w/2;
                    iw = Math.random() * -w/16 - 1;
                }
                if (ew < w/8) {
                    ew = w/8;
                    iw = Math.random() * w/16 + 1;
                }
                if (eh > h/2) {
                    eh = h/2;
                    ih = Math.random() * -h/16 - 1;
                }
                if (eh < h/8) {
                    eh = h/8;
                    ih = Math.random() * h/16 + 1;
                }
                if ((x+ew) > w) {
                    x = (w - ew)-1;
                    ix = Math.random() * -w/32 - 1;
                }
                if (x < 0) {
                    x = 2;
                    ix = Math.random() * w/32 + 1;
                }
                if ((y+eh) > h) {
                    y = (h - eh)-2;
                    iy = Math.random() * -h/32 - 1;
                }
                if (y < 0) {
                    y = 2;
                    iy = Math.random() * h/32 + 1;
                }
            }
    

            /* 
             * reset x & y coordinates and ellipse width & height
             */
            public void reset(int w, int h) {
                x = Math.random()*w;
                y = Math.random()*h;
                ew = (Math.random()*w)/2;
                eh = (Math.random()*h)/2;
            }
        } // End AnimVal class


    } // End Demo class



    /**
     * The DemoControls class adds buttons for showing up to three
     * clips and a button to display numerous objects (Images, 
     * TexturePaint, GradientPaint and a String)
     */
    static class DemoControls extends JPanel implements ActionListener {

        Demo demo;
        JToolBar toolbar;

        public DemoControls(Demo demo) {
            this.demo = demo;
            setBackground(Color.gray);
            add(toolbar = new JToolBar());
            toolbar.setFloatable(false);
            addTool("Objects", true);
            addTool("Clip1", true);
            addTool("Clip2", false);
            addTool("Clip3", false);
        }


        public void addTool(String str, boolean state) {
            JButton b = (JButton) toolbar.add(new JButton(str));
            b.setBackground(state ? Color.green : Color.lightGray);
            b.setSelected(state);
            b.addActionListener(this);
        }


        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            b.setSelected(!b.isSelected());
            b.setBackground(b.isSelected() ? Color.green : Color.lightGray);
            if (b.getText().equals("Objects")) {
                demo.doObjects = b.isSelected();
            } else if (b.getText().equals("Clip1")) {
                demo.animval[0].isSelected = b.isSelected();
            } else if (b.getText().equals("Clip2")) {
                demo.animval[1].isSelected = b.isSelected();
            } else if (b.getText().equals("Clip3")) {
                demo.animval[2].isSelected = b.isSelected();
            }
        }
    } // End DemoControls class



    public static void main(String argv[]) {
        final ClipAnim demo = new ClipAnim();
        demo.init();
        Frame f = new Frame("Java 2D(TM) Demo - ClipAnim");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
            public void windowDeiconified(WindowEvent e) { demo.start(); }
            public void windowIconified(WindowEvent e) { demo.stop(); }
        });
        f.add(demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
        demo.start();
    }
}


