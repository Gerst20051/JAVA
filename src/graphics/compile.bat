@echo ------------------------------------------------------------------
@echo compile.bat - Use to compile all Java 2D sample program on
@echo               Windows systems.
@echo               This script assumes 'javac' is in your executable
@echo               path and you are using JDK 1.2 FCS or greater
@echo ------------------------------------------------------------------
cd Arcs_Curves
javac *.java
cd ..\Clipping
javac *.java
cd ..\Colors
javac *.java
cd ..\Composite
javac *.java
cd ..\Fonts
javac *.java
cd ..\Image
javac *.java
cd ..\Lines
javac *.java
cd ..\Paint
javac *.java
cd ..\Paths
javac *.java
cd ..\Transforms
javac *.java
cd ..
