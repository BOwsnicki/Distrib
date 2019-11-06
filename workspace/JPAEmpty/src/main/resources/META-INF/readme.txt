1.  This is a framework to set up a usable JPA/Maven project. It contains
    a. A pom.xml with the necessary dependencies
    b. A correctly located persistence.xml = src/main/resources/META-INF/persistence.xml
    c. A "dummy" main (Main.java) in the root folder
    Both of these probably need to be edited!


2.  Validate this project:
    a.  Right click pom.xml --> Run Maven
    b.  Goal: clean test
    It prints a lot, but at the end
        Hello, world - from Main.java
        ------------------------------------------------------------------------
        BUILD SUCCESS


3.  Project name, package folder names and the main class might have changed in a new Maven project. 
    This concerns mostly pom.xml and persistence.xml

    What needs be edited in pom.xml:

    <configuration>  
        <mainClass>com.mycompany.jpaempty.Main</mainClass>  
    </configuration>  
    needs to reference the full path of the application class in the project

    <groupId>com.mycompany</groupId>
    <artifactId>JPAEmpty</artifactId>
    <name>JPAEmpty</name>
    need to be changed accordingly


    What needs to be edited in persistence.xml:
    
    Add for each Entity class "C" add 
        <class>com.mycompany.jpaempty.C</class>
    before the "properties" element (watch the real package names!)

    The properties for
        javax.persistence.jdbc.url
        javax.persistence.jdbc.user
        javax.persistence.jdbc.driver
        javax.persistence.jdbc.password
    need to be set correctly for the application


    4.  Setup for a "DB first" application

    a.  Select an existing DB connection for the DB in question - for MySQL the built-in mysql connection does the trick
    b.  Run the DDL/Population script
    c.  Create a connection for the newly created DB (only once)
    d.  Create Entity Classes from your DB
        d1. Select New --> Entity Classes from Database 
        d2. Select the corresponding DB connection from the drop-down menu
        d3. Select/Add the tables you want to use
        d4. "Next" - accept defaults and "Finish"
    e.  Write your application code around these classes
    f.  Test/Run via Maven goal "clean test"


    5.  Setup for an "Objects first" application

    a.  Select an existing DB connection for the DB in question - for MySQL the built-in mysql connection does the trick
    b.  Run a script just containing 
            DROP DATABASE IF EXISTS <DBName>;
            CREATE DATABASE <DBName>;
        no tables, nothing - the mapper will do that
    c.  Create a connection for the newly created DB (only once)
    d.  Write your Entity classes
    e.  Write your application code around these classes
    f.  Test/Run via Maven goal "clean test"
    g.  Inspect your DB structure under the "Services" tab
    h.  Repeat step b. if you want to reset your DB
