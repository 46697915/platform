@echo off
set path=C:\Program Files\Java\jdk1.8.0_131\bin
rem start批处理中调用外部程序的命令，否则等外部程序完成后才继续执行剩下的指令
START "demo" "%path%\javaw" -jar platform-test.jar 
echo platform-test is start!
pause