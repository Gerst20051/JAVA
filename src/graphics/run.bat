@echo ---------------------------------------------------------
@echo run.bat - Use to run all the Java 2D sample programs on
@echo           Windows systems.
@echo           This assumes "java" is in your executable path,
@echo           and you are using JDK 1.2 FCS or greater
@echo ---------------------------------------------------------
cd Arcs_Curves
java Arcs
java BezierAnim
java Curves
java Ellipses
cd ..\Clipping
java Areas
java ClipAnim
java Intersection
java Text
cd ..\Colors
java Balls
java BullsEye
java ColorConvert
java Rotator3D
cd ..\Composite
java ACimages
java ACrules
java FadeAnim
cd ..\Fonts
java AllFonts
java AttributedStr
java Highlighting
java Outline
java Tree
cd ..\Image
java DukeAnim
java ImageOps
java JPEGFlip
java WarpImage
cd ..\Lines
java Dash
java LineTest
java LineAnim
cd ..\Paint
java GradAnim
java GradientBurst
java Texture
java TextureAnim
cd ..\Paths
java Append
java CurveQuadTo
java FillStroke
java Stars3D
java WindingRule
cd ..\Transforms
java Rotate
java SelectTx
java TransformAnim
cd ..
