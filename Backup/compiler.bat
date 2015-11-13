@echo off
title Server Compiler
echo **** Compiling please wait ****
"C:\Program Files (x86)\Java\jdk1.6.0_18\bin\javac.exe" -d bin -cp . *.java
echo **** Compiling finished! ****
pause