@echo off
echo.
call %~dp0\setenv.bat
echo.
echo.
start cmd /k java -DSTOP.PORT=9080 -DSTOP.KEY=jettynaz -jar %JETTY_HOME%\start.jar %*
popd
echo done.