                         Java(TM) 2D Sample Programs

These sample programs demonstrate new Java(TM) 2D features available in the
Java Development Kit, version 1.2 -- you will need to download JDK(TM) 1.2 to
run these programs.


List of Sample Programs
=======================

  Directory  Java Files        Description
Arcs_Curves  Arcs.java         Arc2D Open, Chord, & Pie arcs; Animated Pie
                               Arc.
             BezierAnim.java   Animated Bezier Curve includes controls for
                               changing graphic attributes.
             Curves.java       CubicCurve2D & QuadCurve2D curves includes
                               FlatteningPathIterator example.
             Ellipses.java     Ellipse2D 25 animated expanding ellipses.
   Clipping  Areas.java        CAG (Constructive Area Geometry) operations : 
                               Add(union), Subtract, Intersect, and ExclusiveOR.
             ClipAnim.java     Animated clipping of an image & composited
                               shapes.
             Intersection.java Animated intersection clipping of rectangle or
                               text.
             Text.java         Clipping an image, lines, and text with text.
     Colors  Balls.java        Animated colored balls bouncing.
             BullsEye.java     Creating colors with an alpha value.
             ColorConvert.java ColorConvertOp a ColorSpace.TYPE_RGB 
                               BufferedImage to ColorSpace.CS_GRAY.
             Rotator3D.java    3D objects with color & lighting translated,
                               rotated and scaled.
  Composite  ACimages.java     Compositing shapes on images.
             ACrules.java      All the AlphaCompositing rules demonstrated. 
             FadeAnim.java     Animation of compositing shapes, text, and
                               images fading in and out.
      Fonts  AllFonts.java     Scrolling text of fonts returned from
                               GraphicsEnvironment.getAllFonts().
             AttributedStr.java Build an AttributedString and then render 
                               the string broken over lines.
             Highlighting.java Highlighting of text showing the caret, the 
                               highlight & the character advances. 
             Outline.java      Rendering text as an outline shape.
             Tree.java         Transformation of characters.
      Image  DukeAnim.java     Animated gif with a transparent background.
             ImageOps.java     Images drawn using operators such as
                               ConvolveOp LowPass & Sharpen, LookupOp and
                               RescaleOp.
             JPEGFlip.java     Render a stroked star into a BufferedImage
                               save the BufferedImage as a JPEG, display
                               both.
             WarpImage.java    Warps a image on a CubicCurve2D flattened
                               path.
      Lines  Dash.java         Various shapes stoked with a dashing pattern.
             LineAnim.java     Lines & Paths animation illustrating all of
                               BasicStroke attributes.
             LineTest.java     Modify a star's BasicStroke attributes.
      Paint  GradAnim.java     GradientPaint animation.
             GradientBurst.java  GradientPaint burst effect with controls 
                               for different colors.
             Textures.java     TexturePaint of image, text and shapes.
             TextureAnim       Texture animation with controls for 
                               transformations.
      Paths  Append.java       Simple append of rectangle to path with and
                               without the connect.
             CurveQuadTo.java  Cubic & Quad curves implemented through
                               GeneralPath.
             FillStroke.java   Basic implementation of GeneralPath, filling
                               & drawing a path.
             WindingRule.java  Rectangles filled to illustrate the
                               GeneralPath winding rule, determining the
                               interior of a path.
 Transforms  ObjectAnim.java   Animation of shapes, text and images
                               rotating, scaling and translating around a
                               canvas.
             Rotate.java       Simple 45 degree rotate of a rectangle.
             Scale.java        Scaling of a rectangle.
             Shear.java        Shearing a image.
              

Compiling the Sample Programs
=============================

You can compile the samples by changing to the sample program's directory and
compiling the *.java files, e.g.

     cd Arcs_Curves
     javac *.java

     (This assumes javac is in your executible path, and you are using
     JDK 1.2 FCS or greater)

For your convenience, we have provided a script to compile all the samples --
execute compile.bat (for Windows) or compile.sh (for Solaris).


Running the Sample Programs
===========================

These sample programs are designed to run as standalone Java applications.
To run them, you need to change to the sample program's directory and
execute the java command on the class name, e.g.

     cd Arcs_Curves
     java Arcs

     (This assumes java is in your executible path, and you are using
     JDK 1.2 FCS or greater)

For your convenience, we have provided another script to run the samples --
execute run.bat (for Windows) or run.sh (for Solaris).
