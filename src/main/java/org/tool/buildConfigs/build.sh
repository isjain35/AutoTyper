#!/bin/bash

# Create the classes directory if it doesn't exist
mkdir -p ../classes
mkdir -p ../JAR

# Compile all Java files to the classes directory
javac -d ../classes ../*.java

# Create a manifest file for the JAR
echo "Main-Class: org.tool.AutoTyper" > manifest.txt

# Create the JAR file
jar cfm ../JAR/AutoTyper.jar manifest.txt -C ../classes .

# Make the JAR executable
chmod +x ../JAR/AutoTyper.jar

# Clean up manifest file
rm manifest.txt

echo "Build complete! Executable JAR file created: AutoTyper.jar"
echo "Run it with: java -jar {DIR_PATH}/AutoTyper.jar"