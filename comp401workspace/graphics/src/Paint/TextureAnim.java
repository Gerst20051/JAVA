/*
 * @(#)TextureAnim.java	1.3  98/12/03
 *
 * Copyright 1997, 1998 by Sun Microsystems, Inc.,
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
import java.awt.image.BufferedImage;
import javax.swing.*;


/**
 * The TextureAnim class demonstrates TexturePaint animation with controls 
 * for selecting the transformations to demonstrate.
 */
public class TextureAnim extends JApplet {

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
     * The Demo class performs animation and painting.
     */
    static class Demo extends JPanel implements Runnable {
    
        // a blue color that is halfway transparent
        public static final Color colorblend = new Color(0f, 0f, 1f, .5f);
        public boolean bouncesize = false;
        public boolean bouncerect = true;
        public boolean rotate = false;
        public boolean shearx = false;
        public boolean sheary = false;
        public boolean showanchor = true;
        private BufferedImage bimgTP;
        private Rectangle tilerect;
        private TexturePaint texture;
        private boolean newtexture;
        private int tilesize;
        private AnimVal w, h, x, y, rot, shx, shy;
        private Thread thread;
        private BufferedImage bimg;
    
    
        public Demo() {
            makeImage(32);

            // the size of the BufferedImage containing the texture
            tilesize = bimgTP.getWidth();

            // initializes the size and location of the texture anchor
            w = new AnimVal(0, 200, 3, 10, tilesize);
            h = new AnimVal(0, 200, 3, 10, tilesize);
            x = new AnimVal(0, 200, 3, 10, 0);
            y = new AnimVal(0, 200, 3, 10, 0);

            rot = new AnimVal(-360, 360, 5, 15, 0);
            shx = new AnimVal(-50, 50, 3, 10, 0);
            shy = new AnimVal(-50, 50, 3, 10, 0);

            // the anchor of the TexturePaint 
            tilerect = new Rectangle(x.getInt(), y.getInt(),
                                     w.getInt(), h.getInt());
            texture = new TexturePaint(bimgTP, tilerect);
        }

    
        // creates the TexturePaint pattern 
        public void makeImage(int size) {
            bimgTP = new BufferedImage(size, size, bimgTP.TYPE_INT_BGR);
            Graphics2D g2d = bimgTP.createGraphics();
            g2d.setColor(Color.white);
            g2d.fillRect(0, 0, size, size);
            for (int j = 0; j < size; j++) {
                float red = j / (float) size;
                for (int i = 0; i < size; i++) {
                    float green = i / (float) size;
                    g2d.setColor(new Color(1.0f - red, 1.0f - green, 0.0f, 1.0f));
                    g2d.drawLine(i, j, i, j);
                }
            }
            newtexture = true;
        }

    
        // resets the limits of the anchor's coordinates
        public void reset(int width, int height) {
            x.newlimits(-width/4, width/4 - w.getInt());
            y.newlimits(-height/4, height/4 - h.getInt());
        }

    
        // adjusts coordinates and size of the texture tile
        public void step(int width, int height) {
            if (tilesize != bimgTP.getWidth()) {
                tilesize = bimgTP.getWidth();
            }

            if (bouncesize) {
                w.anim();
                h.anim();
                x.newlimits(-width/4, width/4 - w.getInt());
                y.newlimits(-height/4, height/4 - h.getInt());
            } else {
                if (w.getInt() != tilesize) {
                    w.set(tilesize);
                    x.newlimits(-width/4, width/4 - w.getInt());
                }
                if (h.getInt() != tilesize) {
                    h.set(tilesize);
                    y.newlimits(-height/4, height/4 - h.getInt());
                }
            }

            if (bouncerect) {
                x.anim();
                y.anim();
            }
            if (newtexture ||
                x.getInt() != tilerect.x || y.getInt() != tilerect.y ||
                w.getInt() != tilerect.width || h.getInt() != tilerect.height)
            {
                newtexture = false;
                int X = x.getInt();
                int Y = y.getInt();
                int W = w.getInt();
                int H = h.getInt();
                tilerect = new Rectangle(X, Y, W, H);
                texture = new TexturePaint(bimgTP, tilerect);
            }
        }
 
   
        // performs the transformations and draws the pattern
        public void drawDemo(int width, int height, Graphics2D g2) {
    
            g2.translate(width/2, height/2);
            if (rotate) {
                rot.anim();
                g2.rotate(Math.toRadians(rot.getFlt()));
            } else {
                rot.set(0);
            }
            if (shearx) {
                shx.anim();
                g2.shear(shx.getFlt()/100, 0.0f);
            } else {
                shx.set(0);
            }
            if (sheary) {
                shy.anim();
                g2.shear(0.0f, shy.getFlt()/100);
            } else {
                shy.set(0);
            }
            g2.setPaint(texture);
            g2.fillRect(-1000, -1000, 2000, 2000);
            if (showanchor) {
                g2.setColor(Color.black);
                g2.setColor(colorblend);
                g2.fill(tilerect);
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
         * The AnimVal class generates new values used to set the
         * coordinates, width and height and transformation factors 
         * for the texture.
         */ 
        static class AnimVal {
            float curval;
            float lowval;
            float highval;
            float currate;
            float lowrate;
            float highrate;
    
            public AnimVal(int lowval, int highval,
                           int lowrate, int highrate) {
                this.lowval = lowval;
                this.highval = highval;
                this.lowrate = lowrate;
                this.highrate = highrate;
                this.curval = randval(lowval, highval);
                this.currate = randval(lowrate, highrate);
            }
    
            public AnimVal(int lowval, int highval,
                           int lowrate, int highrate,
                           int pos) {
                this(lowval, highval, lowrate, highrate);
                set(pos);
            }
    
            public float randval(float low, float high) {
                return (float) (low + Math.random() * (high - low));
            }

            public float getFlt() {
                return curval;
            }
    
            public int getInt() {
                return (int) curval;
            }

            public void anim() {
                curval += currate;
                clip();
            }
    
            public void set(float val) {
                curval = val;
                clip();
            }
    
            public void clip() {
                if (curval > highval) {
                    curval = highval - (curval - highval);
                    if (curval < lowval) {
                        curval = highval;
                    }
                    currate = - randval(lowrate, highrate);
                } else if (curval < lowval) {
                    curval = lowval + (lowval - curval);
                    if (curval > highval) {
                        curval = lowval;
                    }
                    currate = randval(lowrate, highrate);
                }
            }
       
            public void newlimits(int lowval, int highval) {
                this.lowval = lowval;
                this.highval = highval;
                clip();
            }
        }  // End AnimVal class

    } // End Demo class



    /**
     * The DemoControls class provides buttons for transforming the
     * texture, showing the blue anchor and dynamically resizing 
     * the texture.
     */
    static class DemoControls extends JPanel implements ActionListener {

        Demo demo;
        JToolBar toolbar;
        JComboBox combo;

        public DemoControls(Demo demo) {
            this.demo = demo;
            setBackground(Color.gray);
            add(toolbar = new JToolBar());
            toolbar.setFloatable(false);
            addTool("BO", "bounce", true);
            addTool("SA", "show anchor", true);
            addTool("RS", "resize", false);
            addTool("RO", "rotate", false);
            addTool("SX", "shear x", false);
            addTool("SY", "shear y", false);
            add(combo = new JComboBox());
            combo.addActionListener(this);
            combo.addItem("8");
            combo.addItem("16");
            combo.addItem("32");
            combo.addItem("64");
            combo.setSelectedIndex(2);
        }


        public void addTool(String str, String toolTip, boolean state) {
            JButton b = (JButton) toolbar.add(new JButton(str));
            b.setBackground(state ? Color.green : Color.lightGray);
            b.setSelected(state);
            b.setToolTipText(toolTip);
            b.addActionListener(this);
        }


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JComboBox) {
                int size = Integer.parseInt((String) combo.getSelectedItem());
                demo.makeImage(size);
                return;
            }
            JButton b = (JButton) e.getSource();
            b.setSelected(!b.isSelected());
            b.setBackground(b.isSelected() ? Color.green : Color.lightGray);
            if (b.getText().equals("BO")) {
                demo.bouncerect = b.isSelected();
            } else if (b.getText().equals("SA")) {
                demo.showanchor = b.isSelected();
            } else if (b.getText().equals("RS")) {
                demo.bouncesize = b.isSelected();
            } else if (b.getText().equals("RO")) {
                demo.rotate = b.isSelected();
            } else if (b.getText().equals("SX")) {
                demo.shearx = b.isSelected();
            } else if (b.getText().equals("SY")) {
                demo.sheary = b.isSelected();
            }
        }
    } // End DemoControls class


    public static void main(String argv[]) {
        final TextureAnim demo = new TextureAnim();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - TextureAnim");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
            public void windowDeiconified(WindowEvent e) { demo.start(); }
            public void windowIconified(WindowEvent e) { demo.stop(); }
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
        demo.start();
    }
}
