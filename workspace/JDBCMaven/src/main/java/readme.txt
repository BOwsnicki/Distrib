
These are the steps to make this project run

1. It's a MAVEN project, so it's all set to be built:
   a. Right click on the project, chose "Build with Dependencies" - that might take a moment when done the first time
   b. You can now run all main classes as usual

2. To make NB work as a DB client, first set up the mysql driver manually
   a. In the "Services" tab, expand "Databases"
   b. Right click "Databases" and select "New Connection"
   c. Under "Locate Driver" select "MySQL (Connector/J Driver)"
   d. It will not show a Driver File, so add one by clicking "ADD"
   e. Navigate to your .m2 folder and down from there:
      .m2/repository/mysql/mysql-connector-java/<version>/mysql-connector-java-<version>.jar

3. Establish the connection
   a. Enter the root password "mysql4me" into the box 
   b. In the JDBC URL box you will see 
      jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull
   c. Manually change that to
      jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC&zeroDateTimeBehavior=convertToNull
      meaning you insert the correct time zone (UTC) into the server
   d. Click finish