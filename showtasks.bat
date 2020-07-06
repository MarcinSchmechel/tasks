call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
goto failruncrud

:openbrowser
start chrome http://localhost:8080/crud/v1/task/getTasks

:failruncrud
echo.
echo There were errors in runcrud.bat

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.