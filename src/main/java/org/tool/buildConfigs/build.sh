#!/bin/bash
set -e

# Create the classes directory if it doesn't exist
mkdir -p ../classes

# Compile all Java files to the classes directory
javac -d ../classes ../*.java

echo "Compilation complete! Classes saved in ../classes"

# Run package.sh located in the same folder as compile.sh
chmod +x package.sh
"$(dirname "$0")/package.sh"
