@echo off
start java -cp ..\lib\hsqldb.jar org.hsqldb.server.Server --database.0 file:vegadb --dbname.0 vega
