@echo off
set CURRENT_DIR=%~dp0
echo CURRENT_DIR : %CURRENT_DIR%

set JETTY_HOME=%CURRENT_DIR%home
echo JETTY_HOME : %JETTY_HOME%

set JETTY_BASE=%CURRENT_DIR%base
echo JETTY_BASE : %JETTY_BASE%

pushd %JETTY_BASE%
echo changed directory to %JETTY_BASE%
