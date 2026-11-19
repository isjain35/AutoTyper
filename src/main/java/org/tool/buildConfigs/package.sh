#!/bin/bash

# Create the JAR directory if it doesn't exist
mkdir -p ../JAR

# Create a manifest file with a guaranteed trailing newline
printf "Main-Class: org.tool.AutoTyper\n" > manifest.txt

# Create the JAR file from existing classes
jar cfm ../JAR/AutoTyper.jar manifest.txt -C ../classes .

# Make the JAR executable
chmod +x ../JAR/AutoTyper.jar

# Clean up manifest file
rm manifest.txt

echo "Build complete! Executable JAR file created: ../JAR/AutoTyper.jar"
echo "Run it with: java -jar ../JAR/AutoTyper.jar"
