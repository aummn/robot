Introduction
=========================
This project 'robot' employs Java to simulate Robot movement on a table with dimension 5 units X 5 units.
Any questions, please contact James at (canglangke001@gmail.com).


Tech Stack:
=========================================================
Java, PowerMock, Mockito, Junit, JUnitParams, Maven

Design Patterns:
=========================================================
Factory pattern, command pattern, builder pattern, Singleton etc


Project Structure
=========================================================
This project is a Maven project with following folders

src/main/java      -  source code 
src/test/java      -  test code
src/main/resources -  property file
src/test/resources -  test data files

com.aummn.tr               - entry class 'App.java'
com.aummn.tr.command       - command factory and command classes
com.aummn.tr.data.input    - data input class (file input or command line input)
com.aummn.tr.exception     - Global exception handler and exception classes

com.aummn.tr.model         - model classes for Position and Facing
com.aummn.tr.parser        - Parse class for input validation and parsing
com.aummn.tr.service       - Game service class
com.aummn.tr.table         - Game table class
com.aummn.tr.util          - File util class


Command Input Modes
=========================================================
Two types of input modes are supported:

mode 1. file input
Test command files can be found in folder 'src/test/resources',  commands are organized into individual files.
New test file can be added in folder 'src/test/resources'.

mode 2. command line input
Commands can be input from command line. 


To switch 2 modes, please go to main method in App.java in package 'com.aummn.tr', comment one of line below

//    	DataInput dataInput = new CLIDataInputImpl();    // command line input
		DataInput dataInput = new FileDataInputImpl(INPUT_FILE_FOLDER, FILE_LISTS);  // command file input

The above would enable File input mode and disable command line mode.


Building
=========================================================
Since this is a Maven based project, it can be easily packaged as an executable jar with Maven command.

Please ensure Maven is installed and configured, then go to project folder, 
for example '/c/dev/git/projects/robot', type the following Maven command to package the application:

Administrator@AUMMN MINGW64 /c/dev/git/projects/robot
$ mvn clean package


This would create the executable jar file 'robot-1.0-SNAPSHOT.jar' in target folder

[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ robot ---
[INFO] Building jar: C:\dev\git\projects\robot\target\robot-1.0-SNAPSHOT.jar
[INFO]
[INFO] --- maven-shade-plugin:3.2.0:shade (default) @ robot ---
[INFO] Replacing original artifact with shaded artifact.
[INFO] Replacing C:\dev\git\projects\robot\target\robot-1.0-SNAPSHOT.jar with C:\dev\git\projects\robot\target\robot-1.0-SNAPSHOT-shaded.jar



Launching
=========================================================
Ensure java is installed and configured, go to the folder containing jar file 'robot-1.0-SNAPSHOT.jar',
type the following Java command to launch the application:

java -jar robot-1.0-SNAPSHOT.jar


Outputs
=========================================================
When the input file is "test_file_move_cmd.txt", the app outputs look like below:

$ java -jar target/robot-1.0-SNAPSHOT.jar
Valid Robot commands are: 'PLACE X, Y, NORTH', 'MOVE', 'LEFT', 'RIGHT', 'REPORT'

Input command: PLACE 0, 1, NORTH
Input command: MOVE
Input command: MOVE
Input command: MOVE
Input command: MOVE
Input command: LEFT
Input command: RIGHT
Input command: RIGHT
Input command: REPORT
Robot position: x = [0] y = [5] facing = [East]




Testing
=========================================================
Test data files in folder 'src/test/resources' have passed testing.
Manual command testing also done via command line.


ToDo
=========================================================
Future work may involve supporting more commands, adding logs, thread safety etc.


 