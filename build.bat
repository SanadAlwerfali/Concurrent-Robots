@echo off

rem Create a directory for compiled class files 
mkdir bin 

rem Compile the Java code
javac -d bin src/Main.java

rem Run the program
java -cp bin Main