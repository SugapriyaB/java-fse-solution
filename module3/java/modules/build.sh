#!/bin/bash
echo "Compiling Java Modules..."

# Create directories for compiled classes
mkdir -p mods/com.utils
mkdir -p mods/com.greetings

# Compile the utils module
javac -d mods/com.utils \
    com.utils/module-info.java \
    com.utils/com/utils/string/StringUtils.java \
    com.utils/com/utils/time/TimeUtils.java

# Compile the greetings module
javac --module-path mods -d mods/com.greetings \
    com.greetings/module-info.java \
    com.greetings/com/greetings/Greeter.java

echo
echo "Running the application..."
echo

# Run the application
java --module-path mods -m com.greetings/com.greetings.Greeter John 