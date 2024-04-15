@echo off
set SRC_DIR=src
set OUT_DIR=out
set LIB_DIR=lib
set MAIN_FILE=MainApp.java
set MYSQL_JAR=mysql-connector-java-8.3.0.jar

:: Compile frontEnd package
for /R "%SRC_DIR%\FrontEnd" %%F in (*.java) do (
    javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" "%%F"
)

:: Compile backEnd package
for /R "%SRC_DIR%\BackEnd" %%F in (*.java) do (
    javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" "%%F"
)

:: Compile main class
javac -d "%OUT_DIR%" -cp "%OUT_DIR%;%LIB_DIR%\%MYSQL_JAR%" "%SRC_DIR%\%MAIN_FILE%"

:: Check for compilation errors
if %ERRORLEVEL% EQU 0 (
    echo Build successful
) else (
    echo Build failed
    exit /b 1
)
