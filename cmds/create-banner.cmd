@ECHO off

SET /p text=Enter the text for the banner: 

figlet %text% > banner.txt