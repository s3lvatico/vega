@echo off

call setenv.bat

echo.
echo.
java -jar %JETTY_HOME%\start.jar --help
popd
