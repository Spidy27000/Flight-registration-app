@echo off
set SRC_DIR=src
set OUT_DIR=out
set LIB_DIR=lib
set MAIN_FILE=MainApp.java
set MYSQL_JAR=mysql-connector-java-8.3.0.jar

:: Compile backEnd package
dir /b /s "%SRC_DIR%\BackEnd\*.java" > "%TEMP%\backEnd_files.txt"
javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" @"%TEMP%\backEnd_files.txt"
del "%TEMP%\backEnd_files.txt"

:: Compile frontEnd package
dir /b /s "%SRC_DIR%\FrontEnd\*.java" > "%TEMP%\frontEnd_files.txt"
javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" @"%TEMP%\frontEnd_files.txt"
del "%TEMP%\frontEnd_files.txt"

:: Compile main class
javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" "%SRC_DIR%\%MAIN_FILE%"

:: Check for compilation errors
if %ERRORLEVEL% EQU 0 (
    echo Build successful
) else (
    echo Build failed
    exit /b 1
)

