@ECHO off

SET /p path=Enter the path to pom.xml file: 
.\mvnw install -f %path%