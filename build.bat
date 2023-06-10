@echo off

REM Set the paths and filenames
set JAVA_HOME="C:\Program Files\Java\jdk-17.0.1"
set SOURCE_DIR=src
set OUTPUT_DIR=bin

REM Compile the Java source file
echo Compiling Main.java...
mkdir %OUTPUT_DIR%
%JAVA_HOME%\bin\javac -d %OUTPUT_DIR% %SOURCE_DIR%\*.java

REM Check if compilation was successful
if %errorlevel% neq 0 (
    echo Compilation failed.
    exit /b
)

REM Execute the Java application
echo Running Main...
%JAVA_HOME%\bin\java -cp %CLASSPATH% Main

REM Cleanup compiled files (optional)
echo Cleaning up...
rmdir /s /q %OUTPUT_DIR%