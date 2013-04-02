/*
 * @(#)ImageOps.java	1.6	98/12/03
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
package Image;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.*;
import java.net.URL;


/**
 * The ImageOps class displays images drawn using operators such as 
 * ConvolveOp LowPass & Sharpen, LookupOp and RescaleOp.
 */
public class ImageOps extends JApplet {

    Demo demo;

    public void init() {
        getContentPane().add(demo = new Demo());
        getContentPane().add("North", new DemoControls(demo));
        getContentPane().add("West", demo.slider1);
        getContentPane().add("East", demo.slider2);
    }



    /**
     * The Demo class renders the selected image with the selected
     * Image Operation filter.
     */
    static class Demo extends JPanel implements ChangeListener {
    
        private static String imgName[] = { "bld.jpg", "boat.gif" };
        private static BufferedImage img[] = new BufferedImage[imgName.length];
        private static String opsName[] = { 
                  "Threshold", "RescaleOp" ,"Invert", "Yellow Invert", "3x3 Blur", 
                  "3x3 Sharpen", "3x3 Edge", "5x5 Edge"};
        private static BufferedImageOp biop[] = new BufferedImageOp[opsName.length];
        private static int rescaleFactor = 128;
        private static float rescaleOffset = 0;
        private static int low = 100, high = 200;
        private int opsIndex, imgIndex;
     
        static {

            /*
             * initializes the threshold operation with the specified
             * low and high ranges.  Any pixel value below 100 is
             * assigned the value 0, while every value above 200 is
             * assigned the value 256.
             */ 
            thresholdOp(low, high);
            int i = 1;

            // multiplies pixel value by 1.0f and adds offset of 0.
            biop[i++] = new RescaleOp(1.0f, 0, null);
            byte invert[] = new byte[256];
            byte ordered[] = new byte[256];
            for (int j = 0; j < 256 ; j++) {
                invert[j] = (byte) (256-j);
                ordered[j] = (byte) j;
            }

            // used to invert bytes
            biop[i++] = new LookupOp(new ByteLookupTable(0,invert), null);

            // used to invert the the bytes for the red and green components
            byte[][] yellowInvert = new byte[][] { invert, invert, ordered };
            biop[i++] = new LookupOp(new ByteLookupTable(0,yellowInvert), null);

            int dim[][] = {{3,3}, {3,3}, {3,3}, {5,5}};

            // holds the kernels used with ConvolveOp
            float data[][] = { {0.1f, 0.1f, 0.1f,              // 3x3 blur
                                0.1f, 0.2f, 0.1f,
                                0.1f, 0.1f, 0.1f},
                               {-1.0f, -1.0f, -1.0f,           // 3x3 sharpen
                                -1.0f, 9.0f, -1.0f,
                                -1.0f, -1.0f, -1.0f},
                               { 0.f, -1.f,  0.f,                  // 3x3 edge
                                -1.f,  5.f, -1.f,
                                 0.f, -1.f,  0.f},
                               {-1.0f, -1.0f, -1.0f, -1.0f, -1.0f,  // 5x5 edge
                                -1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
                                -1.0f, -1.0f, 24.0f, -1.0f, -1.0f,
                                -1.0f, -1.0f, -1.0f, -1.0f, -1.0f,
                                -1.0f, -1.0f, -1.0f, -1.0f, -1.0f}};
            for (int j = 0; j < data.length; j++, i++) {
                biop[i] = new ConvolveOp(new Kernel(dim[j][0],dim[j][1],data[j]));
            }
        }
        private BufferedImage bimg;
        protected JSlider slider1, slider2;
    
    
        public Demo() {
            setBackground(Color.white);
            for (int i = 0; i < imgName.length; i++) {
                Image image = getImage(imgName[i]);
                int iw = image.getWidth(this);
                int ih = image.getHeight(this);
                img[i] = new BufferedImage(iw, ih, BufferedImage.TYPE_INT_RGB);
                img[i].createGraphics().drawImage(image,0,0,null);
            }
            slider1 = new JSlider(JSlider.VERTICAL, 0, 255, low);
            slider1.setPreferredSize(new Dimension(15, 100));
            slider1.addChangeListener(this);
            slider2 = new JSlider(JSlider.VERTICAL, 0, 255, high);
            slider2.setPreferredSize(new Dimension(15, 100));
            slider2.addChangeListener(this);
        }
    
    
        public Image getImage(String name) {
            URL url = ImageOps.class.getResource(name);
            Image img = getToolkit().getImage(url);
            try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(img, 0);
                tracker.waitForID(0);
            } catch (Exception e) {}
            return img;
        }
    

        /*
         * specifies a low value, and high value to control the color
         * component values for each pixel of an image.  Puts the color 
         * values into a single byte array.  The single byte array is more
         * suited for gray scale images, [][] byte array would be more
         * appropriate for RGB images.  
         */ 
         public static void thresholdOp(int low, int high) {
            byte threshold[] = new byte[256];
            for (int j = 0; j < 256 ; j++) {
                if (j > high) {
                    threshold[j] = (byte) 255;
                } else if (j < low) {
                    threshold[j] = (byte) 0;
                } else {
                    threshold[j] = (byte) j;
                }
            }
            biop[0] = new LookupOp(new ByteLookupTable(0,threshold), null);
        }
    

        // filters the image with the selected filter and draws it
        public void drawDemo(int w, int h, Graphics2D g2) {
            int iw = img[imgIndex].getWidth(null);
            int ih = img[imgIndex].getHeight(null);
            BufferedImage bimg = new BufferedImage(iw,ih,BufferedImage.TYPE_INT_RGB);
            biop[opsIndex].filter(img[imgIndex], bimg);
            g2.drawImage(bimg,0,0,w,h,null);
        }
    
    
        public Graphics2D createGraphics2D(int w, int h) {
            Graphics2D g2 = null;
            if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
                bimg = (BufferedImage) createImage(w, h);
            } 
            g2 = bimg.createGraphics();
            g2.setBackground(getBackground());
            g2.clearRect(0, 0, w, h);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                                RenderingHints.VALUE_RENDER_QUALITY);
            return g2;
        }
    
    
        public void paint(Graphics g) {
            Dimension d = getSize();
            Graphics2D g2 = createGraphics2D(d.width, d.height);
            drawDemo(d.width, d.height, g2);
            g2.dispose();
            g.drawImage(bimg, 0, 0, this);
        }
    
    
        public void stateChanged(ChangeEvent e) {
            if (e.getSource().equals(slider1)) {
                if (opsIndex == 0) {
                    thresholdOp(slider1.getValue(), high);
                } else {
                    rescaleFactor = slider1.getValue();
                    biop[1] = new RescaleOp((float)rescaleFactor/128.0f, rescaleOffset, null);
                }
            } else {
                if (opsIndex == 0) {
                    thresholdOp(low, slider2.getValue());
                } else {
                    rescaleOffset = (float) slider2.getValue();
                    biop[1] = new RescaleOp((float)rescaleFactor/128.0f, rescaleOffset, null);
                }
    
            }
            repaint();
        }
    } // End Demo class



    /**
     * The DemoControls class provides controls for selecting the imaging
     * operation, the image to be filtered and the ranges to be used in
     * the filtering operation.  When rescaling is selected, slider1 
     * controls the rescale factor and slider2 controls the rescale
     * offset.  When thresholding is selected, slider1 controls the
     * low range and slider2 controls the high range of the pixel byte
     * value.  Since the demo only offers two sliders that control a 
     * single byte array of color components, thresholding the gray scale
     * boat.gif image is more practical.  To better demonstrate the use of
     * thresholding with RGB images, six sliders, representing the low and
     * the high range for each color component, would be needed.  If an
     * imaging operation besides rescaling or thresholding is selected,
     * both sliders are disabled.
     */
    static class DemoControls extends JPanel implements ActionListener, Runnable {

        Demo demo;
        JComboBox imgCombo, opsCombo;
        Font font = new Font("serif", Font.PLAIN, 10);
        Thread thread;

        public DemoControls(Demo demo) {
            this.demo = demo;
            setBackground(Color.gray);
            add(imgCombo = new JComboBox());
            imgCombo.setFont(font);
            for (int i = 0; i < Demo.imgName.length; i++) {
                imgCombo.addItem(Demo.imgName[i]);
            }
            imgCombo.addActionListener(this);
            add(opsCombo = new JComboBox());
            opsCombo.setFont(font);
            for (int i = 0; i < Demo.opsName.length; i++) {
                opsCombo.addItem(Demo.opsName[i]);
            }
            opsCombo.addActionListener(this);
            setToolTipText("click to run thread");
            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (thread == null) start(); else stop();
                }
            });
        }


        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(opsCombo)) {
                demo.opsIndex = opsCombo.getSelectedIndex();
                if (demo.opsIndex == 0) {
                    demo.slider1.setValue(demo.low);
                    demo.slider2.setValue(demo.high);
                    demo.slider1.setEnabled(true);
                    demo.slider2.setEnabled(true);
                } else if (demo.opsIndex == 1) {
                    demo.slider1.setValue(demo.rescaleFactor);
                    demo.slider2.setValue((int) demo.rescaleOffset);
                    demo.slider1.setEnabled(true);
                    demo.slider2.setEnabled(true);
                } else {
                    demo.slider1.setEnabled(false);
                    demo.slider2.setEnabled(false);
                }
            } else if (e.getSource().equals(imgCombo)) {
                demo.imgIndex = imgCombo.getSelectedIndex();
            }
            demo.repaint();
        }


        public Dimension getPreferredSize() {
            return new Dimension(200,32);
        }


        public void start() {
            if (thread != null) {
                return;
            }
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        }


        public synchronized void stop() {
            if (thread != null) {
                thread.interrupt();
            }
            thread = null;
            notifyAll();
        }


        public void run() {
            Thread me = Thread.currentThread();
            while (thread == me) {
                for (int i = 0; i < Demo.imgName.length; i++) {
                    imgCombo.setSelectedIndex(i);
                    for (int j = 0; j < Demo.opsName.length; j++) {
                        opsCombo.setSelectedIndex(j);
                        if (j <= 1) {
                            for (int k = 50; k <= 200; k+=10) {
                                demo.slider1.setValue(k);
                                try {
                                    thread.sleep(200);
                                } catch (InterruptedException e) { return; }
                            }
                        }
                        try {
                            thread.sleep(4444);
                        } catch (InterruptedException e) { return; }
                    }
                }
            }
            thread = null;
        }
    } // End DemoControls class


    public static void main(String argv[]) {
        final ImageOps demo = new ImageOps();
        demo.init();
        JFrame f = new JFrame("Java 2D(TM) Demo - ImageOps");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        f.getContentPane().add("Center", demo);
        f.pack();
        f.setSize(new Dimension(400,300));
        f.show();
    }
}
