@echo off
set OUT_DIR=out
set LIB_DIR=lib

set MYSQL_JAR=mysql-connector-java-8.3.0.jar

call build.bat

cd %OUT_DIR%

java -cp "../%OUT_DIR%;../%LIB_DIR%/%MYSQL_JAR%" MainApp

cd ..

exit /b
