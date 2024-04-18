@echo off
call build.bat
cd out
java -cp ";..\lib\mysql-connector-j-8.3.0.jar" MainApp
cd ..
exit /b
