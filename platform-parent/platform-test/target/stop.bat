@echo off
set port=8084
rem 取每行的1-5列（以空格分隔），赋值个变量%%i %%j %%k %%l %%m ，注意变量字母连续

:: ^这个是控制命令，要把它们输出到文件，必须在前面加个 ^ 符号
rem /f 将('netstat -ano^|findstr ":%port%"')这个结果每行循环一次
for /f "tokens=1-5" %%i in ('netstat -ano^|findstr ":%port%"') do (
    ::echo %%i %%j %%k %%l %%m 
	rem /i 表示不区分大小写
	if /i "%%l" equ "LISTENING" (
		echo kill the process %%m who use the port %port%
		echo %%m this will kill 
		taskkill /pid %%m /F
		
		rem 跳转命令
		rem goto label #跳转到指定的标签那一行
		goto label
	)
)	
rem :label #行首为:表示该行是标签行，标签行不执行操作
:label
pause