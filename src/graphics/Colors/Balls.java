/*
 * @(#)Balls.java	1.4  98/12/03
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
import java.awt.image.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;


/**
 * The Balls class demonstrates animated color bouncing balls.
 */
public class Balls extends JApplet {

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
     * The Demo class performs the animation and painting.
     */
    static class Demo extends JPanel implements Runnable {
    
        private static Color colors[] = 
                { Color.red, Color.orange, Color.yellow, Color.green.darker(),
                  Color.blue, new Color(75, 00, 82), new Color(238,130,238) };
        private Thread thread;
        private BufferedImage bimg;
        private long now, deltaT, lasttime;
        private boolean active;
        private boolean clearDemo = true;
        protected Ball balls[] = new Ball[colors.length];
    

        /*
         * creates a new ball object for each color in the colors
         * array and selects the first, fourth, fifth and seventh balls
         * for initial display
         */
        public Demo() {
            setBackground(Color.white);
            for (int i = 0; i < colors.length; i++) {
                balls[i] = new Ball(colors[i], 40);
            }
            balls[0].isSelected = true;
            balls[3].isSelected = true;
            balls[4].isSelected = true;
            balls[6].isSelected = true;
        }
        
        
        public void step(int w, int h) {
            if (lasttime == 0) {
                lasttime = System.currentTimeMillis();
            }
            now = System.currentTimeMillis();
            deltaT = now - lasttime;
            active = false;
            for (int i = 0; i < balls.length; i++) {
                if (balls[i] == null) {
                    return;
                }
                balls[i].step(deltaT, w, h);
                if (balls[i].Vy > .02 || -balls[i].Vy > .02 ||
                        balls[i].y + balls[i].bsize < h) {
                    active = true;
                }
            }
            if (!active) {
                for (int i = 0; i < balls.length; i++) {
                    balls[i].Vx = (float)Math.random() / 4.0f - 0.125f;
                    balls[i].Vy = -(float)Math.random() / 4.0f - 0.2f;
                }
            }
        }
    
    
        public void drawDemo(int w, int h, Graphics2D g2) {
            for (int i = 0; i < balls.length; i++) {
                Ball b = balls[i];
                if (b == null || b.imgs[b.index] == null || !b.isSelected) {
                    continue;
                }
                g2.drawImage(b.imgs[b.index], (int) b.x, (int) b.y, this);
            }
            lasttime = now;
        }
    
    
        public Graphics2D createGraphics2D(int w, int h) {
            Graphics2D g2 = null;
            if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
                bimg = (BufferedImage) createImage(w, h);
            } 
            g2 = bimg.createGraphics();
            if (clearDemo) {
                g2.setBackground(getBackground());
                g2.clearRect(0, 0, w, h);
            }
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
         * The Ball class creates 5 balls.  Each ball increasingly blends 
         * the color white with the specified color.  
         */
        static class Ball {
        
            public int bsize;
            public float x, y;
            public float Vx = 0.1f;
            public float Vy = 0.05f;
            public int nImgs = 5;
            public BufferedImage imgs[];
            public int index = (int) (Math.random() * (nImgs-1));
        
            private final float inelasticity = .96f;
            private final float Ax = 0.0f;
            private final float Ay = 0.0002f;
            private final float Ar = 0.9f;
            private final int UP = 0;
            private final int DOWN = 1;
            private int indexDirection = UP;
            private boolean collision_x, collision_y;
            private float jitter;
            private Color color;
            private boolean isSelected;
        
        
            public Ball(Color color, int bsize) {
                this.color = color;
                makeImages(bsize);
            }
        
        
            public void makeImages(int bsize) {
                this.bsize = bsize*2;
                int R = bsize;
                byte[] data = new byte[R * 2 * R * 2];
                int maxr = 0;
                for (int Y = 2 * R; --Y >= 0;) {
                    int x0 = (int) (Math.sqrt(R * R - (Y - R) * (Y - R)) + 0.5);
                    int p = Y * (R * 2) + R - x0;
                    for (int X = -x0; X < x0; X++) {
                        int x = X + 15;
                        int y = Y - R + 15;
                        int r = (int) (Math.sqrt(x * x + y * y) + 0.5);
                        if (r > maxr) {
                            maxr = r;
                        }
                        data[p++] = r <= 0 ? 1 : (byte) r;
                    }
                }
        
                imgs = new BufferedImage[nImgs];
        
                int bg = 255;
                byte red[] = new byte[256];
                red[0] = (byte) bg;
                byte green[] = new byte[256];
                green[0] = (byte) bg;
                byte blue[] = new byte[256];
                blue[0] = (byte) bg;

                // for each image, set its color
                for (int r = 0; r < imgs.length; r++) {
                    float b = 0.5f + (float) ((r+1f)/imgs.length/2f);
                    for (int i = maxr; i >= 1; --i) {
                        float d = (float) i / maxr;
                        red[i] = (byte) blend(blend(color.getRed(), 255, d), bg, b);
                        green[i] = (byte) blend(blend(color.getGreen(), 255, d), bg, b);
                        blue[i] = (byte) blend(blend(color.getBlue(), 255, d), bg, b);
                    }
                    IndexColorModel icm = new IndexColorModel(8, maxr + 1,
                                red, green, blue, 0);
                    DataBufferByte dbb = new DataBufferByte(data, data.length);
                    int bandOffsets[] = {0};
                    WritableRaster wr = Raster.createInterleavedRaster(dbb,
                        R*2,R*2,R*2,1, bandOffsets,null);
                    imgs[r] = new BufferedImage(icm, wr,icm.isAlphaPremultiplied(),null);
                }
            }

        
	    // performs the blending of white and color
            private final int blend(int fg, int bg, float fgfactor) {
                return (int) (bg + (fg - bg) * fgfactor);
            }
        

	    // performs the action of bouncing off the window "walls" 
            public void step(long deltaT, int w, int h) {
                collision_x = false;
                collision_y = false;
        
                jitter = (float) Math.random() * .01f - .005f;
        
                x += Vx * deltaT + (Ax / 2.0) * deltaT * deltaT;
                y += Vy * deltaT + (Ay / 2.0) * deltaT * deltaT;
                if (x <= 0.0f) {
                    x = 0.0f;
                    Vx = -Vx * inelasticity + jitter;
                    collision_x = true;
                }
                if (x + bsize >= w) {
                    x = w - bsize;
                    Vx = -Vx * inelasticity + jitter;
                    collision_x = true;
                }
                if (y <= 0) {
                    y = 0;
                    Vy = -Vy * inelasticity + jitter;
                    collision_y = true;
                }
                if (y + bsize >= h) {
                    y = h - bsize;
                    Vx *= inelasticity;
                    Vy = -Vy * inelasticity + jitter;
                    collision_y = true;
                }
                Vy = Vy + Ay * deltaT;
                Vx = Vx + Ax * deltaT;
        
                if (indexDirection == UP) {
                    index++; 
                }
                if (indexDirection == DOWN) {
                    --index; 
                }
                if (index+1 == nImgs) {
                    indexDirection = DOWN;
                }
                if (index == 0) {
                    indexDirection = UP;
                }
            }
        }  // End Ball class


    } // End Demo class 



    /**
     * The DemoControls class provides controls for choosing which colored
     * ball to display, the size of the balls and whether or not to clear
     * the drawing surface.
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
            addTool("Clear", true);
            addTool("R", demo.balls[0].isSelected);
            addTool("O", demo.balls[1].isSelected);
            addTool("Y", demo.balls[2].isSelected);
            addTool("G", demo.balls[3].isSelected);
            addTool("B", demo.balls[4].isSelected);
            addTool("I", demo.balls[5].isSelected);
            addTool("V", demo.balls[6].isSelected);
            add(combo = new JComboBox());
            combo.addItem("10");
            combo.addItem("20");
            combo.addItem("30");
            combo.addItem("40");
            combo.addItem("50");
            combo.addItem("60");
            combo.addItem("70");
            combo.addItem("80");
            combo.setSelectedIndex(3);
            combo.addActionListener(this);
        }


        public void addTool(String str, boolean state) {
            JButton b = (JButton) toolbar.add(new JButton(str));
            b.setBackground(state ? Color.green : Color.lightGray);
            b.setSelected(state);
            b.addActionListener(this);
        }


        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JComboBox) {
                int size = Integer.parseInt((String) combo.getSelectedItem());
                for (int i = 0; i < demo.balls.length; i++) {
                    demo.balls[i].makeImages(size);
                }
                return;
            }
            JButton b = (JButton) e.getSource();
            b.setSelected(!b.isSelected());
            b.setBackground(b.isSelected() ? Color.green : Color.lightGray);
            if (b.getText().equals("Clear")) {
                demo.clearDemo = b.isSelected();
            } else {
                int index = toolbar.getComponentIndex(b)-1;
                demo.balls[index].isSelected = b.isSelected();
            }
        }
    } // End DemoControls


    public static void main(String argv[]) {
        final Balls demo = new Balls();
        demo.init();
        Frame f = new Frame("Java 2D(TM) Demo - Balls");
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
} // End Balls class
