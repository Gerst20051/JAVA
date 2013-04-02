/*
 * @(#)FadeAnim.java	1.6  98/12/03
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
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.net.URL;


/**
 * The FadeAnim class demonstrates animation of fading and compositing 
 * shapes, text and images.
 */
public class FadeAnim extends JApplet {

    Demo demo;

    public void init() {
        getContentPane().add(demo = new Demo());
        getContentPane().add("East", new DemoControls(demo));
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

        private static TexturePaint texture;

        static {
            int w = 10; int h = 10;
            BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D gi = bi.createGraphics();
            Color oc = Color.blue; Color ic = Color.green;
            gi.setPaint(new GradientPaint(0,0,oc,w*.35f,h*.35f,ic));
            gi.fillRect(0, 0, w/2, h/2);
            gi.setPaint(new GradientPaint(w,0,oc,w*.65f,h*.35f,ic));
            gi.fillRect(w/2, 0, w/2, h/2);
            gi.setPaint(new GradientPaint(0,h,oc,w*.35f,h*.65f,ic));
            gi.fillRect(0, h/2, w/2, h/2);
            gi.setPaint(new GradientPaint(w,h,oc,w*.65f,h*.65f,ic));
            gi.fillRect(w/2, h/2, w/2, h/2);
            texture = new TexturePaint(bi,new Rectangle(0,0,w,h));
        }
        private static BasicStroke bs = new BasicStroke(6); 

        // an array of Font objects
        private static Font fonts[] = {
                    new Font("Times New Roman", Font.PLAIN, 64),
                    new Font("serif", Font.BOLD + Font.ITALIC, 24),
                    new Font("Courier", Font.BOLD, 36),
                    new Font("Arial", Font.BOLD + Font.ITALIC, 48),
                    new Font("Helvetica", Font.PLAIN, 52)};

        // an array of String objects 
        private static String strings[] = {
                    "Alpha", "Composite", "Src", "SrcOver", 
                    "SrcIn", "SrcOut", "Clear", "DstOver", "DstIn" };

        // an array of images
        private static String imgs[] = { 
                    "jumptojavastrip.gif", "duke.gif", "star7.gif" };

        // an array of colors
        private static Paint paints[] = { 
                    Color.red, Color.blue, Color.green, Color.magenta, 
                    Color.orange, Color.pink, Color.cyan, texture,
                    Color.yellow, Color.lightGray, Color.white};
        private Vector vector = new Vector(13);
        public int numShapes, numStrings, numImages;
        private Thread thread;
        private BufferedImage bimg;
    
    
        public Demo() {
            setBackground(Color.black);

            // initializes to display 2 strings, 3 images and 8 shapes
            setStrings(2);
            setImages(3);
            setShapes(8);
        }
    
    
        public Image getImage(String name) {
            URL url = FadeAnim.class.getResource(name);
            Image img = getToolkit().getImage(url);
            try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                tracker.waitForID(0);
            } catch (Exception e) {}
            return img;
        }

    
        // adds images to a Vector
        public void setImages(int num) {
    
            if (num < numImages) {
                Vector v = new Vector(vector.size());
                for (int i = 0; i < vector.size(); i++) {
                    if (((ObjectData) vector.get(i)).object instanceof Image) {
                        v.addElement(vector.get(i));
                    }
                }
                vector.removeAll(v);
                v.setSize(num);
                vector.addAll(v);
            } else {
                Dimension d = getSize();
                for (int i = numImages; i < num; i++) {
                    Object obj = getImage(imgs[i % imgs.length]);
                    if (imgs[i % imgs.length].equals("jumptojavastrip.gif")) {
                        int iw = ((Image) obj).getWidth(null);
                        int ih = ((Image) obj).getHeight(null);
                        BufferedImage bimg = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
                        bimg.createGraphics().drawImage((Image) obj, 0, 0, null);
                        obj = bimg;
                    }
                    ObjectData od = new ObjectData(obj, Color.black);
                    od.reset(d.width, d.height);
                    vector.addElement(od);
                }
            }
            numImages = num;
        }

            
        // adds Strings to a Vector
        public void setStrings(int num) {
    
            if (num < numStrings) {
                Vector v = new Vector(vector.size());
                for (int i = 0; i < vector.size(); i++) {
                    if (((ObjectData) vector.get(i)).object instanceof TextData) {
                        v.addElement(vector.get(i));
                    }
                }
                vector.removeAll(v);
                v.setSize(num);
                vector.addAll(v);
            } else {
                Dimension d = getSize();
                for (int i = numStrings; i < num; i++) {
                    int j = i % fonts.length;
                    int k = i % strings.length;
                    Object obj = new TextData(strings[k], fonts[j], this); 
                    ObjectData od = new ObjectData(obj, paints[i%paints.length]);
                    od.reset(d.width, d.height);
                    vector.addElement(od);
                }
            }
            numStrings = num;
        }

            
        // adds shapes to a Vector
        public void setShapes(int num) {
    
            if (num < numShapes) {
                Vector v = new Vector(vector.size());
                for (int i = 0; i < vector.size(); i++) {
                    if (((ObjectData) vector.get(i)).object instanceof Shape) {
                        v.addElement(vector.get(i));
                    }
                }
                vector.removeAll(v);
                v.setSize(num);
                vector.addAll(v);
            } else {
                Dimension d = getSize();
                for (int i = numShapes; i < num; i++) {
                    Object obj = null;
                    switch (i % 7) {
                        case 0 : obj = new GeneralPath(); break;
                        case 1 : obj = new Rectangle2D.Double(); break;
                        case 2 : obj = new Ellipse2D.Double(); break;
                        case 3 : obj = new Arc2D.Double(); break;
                        case 4 : obj = new RoundRectangle2D.Double(); break;
                        case 5 : obj = new CubicCurve2D.Double(); break;
                        case 6 : obj = new QuadCurve2D.Double(); break;
                    }
                    ObjectData od = new ObjectData(obj, paints[i%paints.length]);
                    od.reset(d.width, d.height);
                    vector.addElement(od);
                }
            } 
            numShapes = num;
        }

    
        // calls ObjectData.reset for each item in vector
        public void reset(int w, int h) {
            for (int i = 0; i < vector.size(); i++) {
                ((ObjectData) vector.get(i)).reset(w, h);
            }
        }

    
        // calls ObjectData.step for each item in vector
        public void step(int w, int h) {
            for (int i = 0; i < vector.size(); i++) {
                ((ObjectData) vector.get(i)).step(w, h);
            }
        }
    
    
        public void drawDemo(int w, int h, Graphics2D g2) {
    
            for (int i = 0; i < vector.size(); i++) {
                ObjectData od = (ObjectData) vector.get(i);
                AlphaComposite ac = AlphaComposite.getInstance(
                                       AlphaComposite.SRC_OVER, od.alpha);
                g2.setComposite(ac);
                g2.setPaint(od.paint);
                g2.translate(od.x, od.y);
    
                if (od.object instanceof Image) {
                    g2.drawImage((Image) od.object, 0, 0, this);
                } else if (od.object instanceof TextData) {
                    g2.setFont(((TextData) od.object).font);
                    g2.drawString(((TextData) od.object).string, 0, 0);
                } else if (od.object instanceof QuadCurve2D 
                        || od.object instanceof CubicCurve2D) 
                {
                    g2.setStroke(bs);
                    g2.draw((Shape) od.object);
                } else if (od.object instanceof Shape) {
                    g2.fill((Shape) od.object);
                }
                g2.translate(-od.x, -od.y);
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
         * The TextData class creates an Object from the specified
         * String and Font and creates a FontMetrics object to store 
         * width and height information.
         */
        static class TextData extends Object {
    
            public String string;
            public Font font;
            public int width, height;
    
            public TextData(String str, Font font, Component cmp) {
                string = str;
                this.font = font;
                FontMetrics fm = cmp.getFontMetrics(font);
                width = fm.stringWidth(str);
                height = fm.getHeight();
            }
        }


    
        /**
         * The ObjectData class increments and decrements the alpha 
         * value used for the fading effect and generates random
         * coordinates for all objects and random sizes for shapes.
         */
        static class ObjectData extends Object {
            final int UP = 0;
            final int DOWN = 1;
            Object object;
            BufferedImage bimg;
            Paint paint;
            double x, y;
            float alpha;
            int alphaDirection;
            int imgX;
    
            public ObjectData(Object object, Paint paint) {
                this.object = object;
                this.paint = paint;
                if (object instanceof BufferedImage) {
                    bimg = (BufferedImage) object;
                    this.object = bimg.getSubimage(0, 0, 80, 80);    
                }
                getRandomXY(300, 250);
                alpha = (float) Math.random();
                alphaDirection = Math.random() > 0.5 ? UP : DOWN;
            }

    
            // gets random coordinate values for object
            private void getRandomXY(int w, int h) {
                if (object instanceof TextData) {
                    x = Math.random() * (w - ((TextData) object).width);
                    y = Math.random() * h;
                    y = y < ((TextData) object).height ? ((TextData) object).height : y;
                } else if (object instanceof Image) {
                    x = Math.random() * (w - ((Image) object).getWidth(null));
                    y = Math.random() * (h - ((Image) object).getHeight(null));
                } else if (object instanceof Shape) {
                    Rectangle bounds = ((Shape) object).getBounds();
                    x = Math.random() * (w - bounds.width);
                    y = Math.random() * (h - bounds.height);
                }
            }

    
            // generates random sizes for object if object is a shape
            public void reset(int w, int h) {
                getRandomXY(w, h);
                double ww = 20 + Math.random()*((w == 0 ? 400 : w)/4);
                double hh = 20 + Math.random()*((h == 0 ? 300 : h)/4);
                if (object instanceof Ellipse2D) {
                    ((Ellipse2D) object).setFrame(0, 0, ww, hh);
                } else if (object instanceof Rectangle2D) {
                    ((Rectangle2D) object).setRect(0, 0, ww, ww);
                } else if (object instanceof RoundRectangle2D) {
                    ((RoundRectangle2D) object).setRoundRect(0, 0, hh, hh, 20, 20); 
                } else if (object instanceof Arc2D) {
                    ((Arc2D) object).setArc(0, 0, hh, hh, 45, 270, Arc2D.PIE);
                } else if (object instanceof QuadCurve2D) {
                    ((QuadCurve2D) object).setCurve(0, 0, w*.2, h*.4, w*.4, 0);
                } else if (object instanceof CubicCurve2D) {
                        ((CubicCurve2D) object).setCurve(0,0,30,-60,60,60,90,0);
                } else if (object instanceof GeneralPath) {
                    GeneralPath p = new GeneralPath();
                    float size = (float) ww;
                    p.moveTo(- size / 2.0f, - size / 8.0f);
                    p.lineTo(+ size / 2.0f, - size / 8.0f);
                    p.lineTo(- size / 4.0f, + size / 2.0f);
                    p.lineTo(+         0.0f, - size / 2.0f);
                    p.lineTo(+ size / 4.0f, + size / 2.0f);
                    p.closePath();
                    object = p;
                }
            }

    
            // increments the alpha value and the BufferedImage subimage
            public void step(int w, int h) {
                if (object instanceof BufferedImage) {
                    if ((imgX += 80) == 800) {
                        imgX = 0;
                    }
                    object = bimg.getSubimage(imgX, 0, 80, 80);    
                }
                if (alphaDirection == UP) {
                    if ((alpha += 0.05) > .99) {
                        alphaDirection = DOWN;
                        alpha = 1.0f;
                    }
                } else if (alphaDirection == DOWN) {
                    if ((alpha -= .05) < 0.01) {
                        alphaDirection = UP;
                        alpha = 0;
                        getRandomXY(w, h);
                    }
                }
            }
        }  // End ObjectData class

    } // End Demo class



    /**
     * The DemoControls class provides controls for choosing the amount
     * of images, shapes or text to display.
     */
    static class DemoControls extends JPanel implements ChangeListener {

        Demo demo;
        JSlider shapeSlider, stringSlider, imageSlider;
        Font font = new Font("serif", Font.PLAIN, 10);

        public DemoControls(Demo demo) {
            this.demo = demo;
            setBackground(Color.gray);
            setLayout(new BorderLayout());
            JToolBar toolbar = new JToolBar(JToolBar.VERTICAL);
            toolbar.setBackground(Color.gray);
            toolbar.setFloatable(false);
            shapeSlider = new JSlider(JSlider.HORIZONTAL,0,20,demo.numShapes);
            shapeSlider.addChangeListener(this);
            TitledBorder tb = new TitledBorder(new EtchedBorder());
            tb.setTitleFont(font);
            tb.setTitle(String.valueOf(demo.numShapes) + " Shapes");
            shapeSlider.setBorder(tb);
            shapeSlider.setPreferredSize(new Dimension(80,45));
            toolbar.addSeparator();
            toolbar.add(shapeSlider);
            toolbar.addSeparator();

            stringSlider = new JSlider(JSlider.HORIZONTAL,0,10,demo.numStrings);
            stringSlider.addChangeListener(this);
            tb = new TitledBorder(new EtchedBorder());
            tb.setTitleFont(font);
            tb.setTitle(String.valueOf(demo.numStrings) + " Strings");
            stringSlider.setBorder(tb);
            stringSlider.setPreferredSize(new Dimension(80,45));
            toolbar.add(stringSlider);
            toolbar.addSeparator();

            imageSlider = new JSlider(JSlider.HORIZONTAL,0,10,demo.numImages);
            imageSlider.addChangeListener(this);
            tb = new TitledBorder(new EtchedBorder());
            tb.setTitleFont(font);
            tb.setTitle(String.valueOf(demo.numImages) + " Images");
            imageSlider.setBorder(tb);
            imageSlider.setPreferredSize(new Dimension(80,45));
            toolbar.add(imageSlider);
            toolbar.addSeparator();

            add(toolbar);
        }


        public void stateChanged(ChangeEvent e) {
            JSlider slider = (JSlider) e.getSource();
            int value = slider.getValue();
            TitledBorder tb = (TitledBorder) slider.getBorder();
            if (slider.equals(shapeSlider)) {
                tb.setTitle(String.valueOf(value) + " Shapes");
                demo.setShapes(value);
            } else if (slider.equals(stringSlider)) {
                tb.setTitle(String.valueOf(value) + " Strings");
                demo.setStrings(value);
            } else if (slider.equals(imageSlider)) {
                tb.setTitle(String.valueOf(value) + " Images");
                demo.setImages(value);
            } 
            slider.repaint();
        }

        public Dimension getPreferredSize() {
            return new Dimension(80,0);
        }
    } // End DemoControls class


    public static void main(String argv[]) {
        final FadeAnim demo = new FadeAnim();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - FadeAnim");
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
