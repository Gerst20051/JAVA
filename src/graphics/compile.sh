#!/bin/sh

echo ""
echo "---------------------------------------------------------------"
echo "compile.sh - Use to compile all Java 2D sample programs"
echo "             on Solaris systems."
echo ""
echo "             This script assumes 'javac' is in your executable"
echo "             path, and you are using JDK 1.2 FCS or greater."
echo "---------------------------------------------------------------"
echo ""

echo "Compiling all java files..."
echo javac */*.java
javac */*.java
echo "...done"

