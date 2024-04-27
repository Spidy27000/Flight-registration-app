@echo off
set SRC_DIR=src
set OUT_DIR=out
set LIB_DIR=lib
set MAIN_FILE=MainApp.java
set MYSQL_JAR=mysql-connector-java-8.3.0.jar

:: Compile backEnd package
javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" "%SRC_DIR%\BackEnd\*.java"

:: Compile frontEnd package
javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" "%SRC_DIR%\FrontEnd\*.java"

:: Compile main class
javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" "%SRC_DIR%\%MAIN_FILE%"

:: Check for compilation errors
if %ERRORLEVEL% EQU 0 (
    echo Build successful
) else (
    echo Build failed
    exit /b 1
)
