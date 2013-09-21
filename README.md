tryjetty
========



Building on Windows

1) Install Java 7 JDK.  This is required for running the application as well as building it

set your JAVA_HOME to the new JDK root directory.  You can do this either in the System Control panel for the whole system
or do it in a single terminal window. 

eg. for a cmd window

set JAVA_HOME=c:\Program Files\Java\jdk1.7.0_40

Add Java into your path with the new JAVA_HOME:

set PATH=%JAVA_HOME%\bin;%PATH%

2) set up Mave, by unpacking it into a directory, and add its bin directory to the PATH

set PATH=%PATH%;c:\Users\Steve\apache-maven-3.1.0\bin

3) Set up Cassandra

4) Install the tables and sample data

You may have to figure out how to run CQLSH in your environment - we're making it easier ...

cd <project>/deployment/CQL

cqlsh -f packages.cql

5) Build the project

mnv install

6) Run a standalone server

Ensure you are in the project root.

Run it as follows:

java -cp "startjetty\target\startjetty-1.0-SNAPSHOT.jar;startjetty\target\lib\*" OneWebApp



