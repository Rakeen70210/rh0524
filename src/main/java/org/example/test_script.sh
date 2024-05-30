#!/bin/bash

# Compile Main.java
javac Main.java

# Check if compilation was successful
if [ $? -eq 0 ]; then

    java Main <<EOF
JAKR
9/3/15
5
101
EOF
else
    echo "Compilation failed."
fi