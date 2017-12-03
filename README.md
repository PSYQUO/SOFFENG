# Tony Joe's Point-of-Sales System

Welcome to SOFENGG S18B's Github Repository!

### How to contribution:
Recommended IDEs
- [Visual Studio Code](https://code.visualstudio.com/) 
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Notepad++](https://notepad-plus-plus.org/)

DBMS 
- [MySQL](https://www.mysql.com/)

Libraries
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/5.1.html)

<hr></hr>

### Setting up database connection parameters

Create a file called dbconfig.ini and place it at the default package (beside App.class).<br>
The dbconfig.ini file should contain the following
```ini
database = <database schema name>
username = <username of database connection>
password = <password of database connection>
```

### How to run w/o IDE or Gradle:

Note: Remember to create/configure the dbconfig.ini at the default package.
```sh
cd src
javac App.java controller/*.java controller/ViewManager/*.java model/*.java model/Transaction/*.java receipt/*.java 
java App
```
