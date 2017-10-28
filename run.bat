@echo off

@echo compiling
javac -d bin src/*.java src/test/*.java src/test/testModel/*.java src/model/*.java

cd bin
@echo initializing
java TestDriver
@echo exit
cd..
