#!/bin/sh

echo ""
echo "---------------------------------------------------------"
echo "run.sh - Use to run all the Java 2D sample programs"
echo "         on Solaris systems."
echo ""
echo "         This assumes "java" is in your executable path,"
echo "         and you are using JDK 1.2 FCS or greater."
echo "---------------------------------------------------------"
echo ""

runit() 
{
	echo "Running $1..."
	java $1
}

cd Arcs_Curves
runit Arcs
runit BezierAnim
runit Curves
runit Ellipses

cd ../Clipping
runit Areas
runit ClipAnim
runit Intersection
runit Text

cd ../Colors
runit Balls
runit BullsEye
runit ColorConvert
runit Rotator3D

cd ../Composite
runit ACimages
runit ACrules
runit FadeAnim

cd ../Fonts
runit AllFonts
runit AttributedStr
runit Highlighting
runit Outline
runit Tree

cd ../Image
runit DukeAnim
runit ImageOps
runit JPEGFlip
runit WarpImage

cd ../Lines
runit Dash
runit LineTest
runit LineAnim

cd ../Paint
runit GradAnim
runit GradientBurst
runit Texture
runit TextureAnim

cd ../Paths
runit Append
runit CurveQuadTo
runit FillStroke
runit Stars3D
runit WindingRule

cd ../Transforms
runit Rotate
runit SelectTx
runit TransformAnim

cd ..
echo "...Done"
