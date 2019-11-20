
This is also the template for your Project 4:

It looks a lot like that TomEE is the better choice to deploy and run JDBC based services than Payara

So, we need to get TomEE on our machine, it's pretty easy:

1. Download (once)

Download the PLUME version over CLI: 
    wget http://mirrors.sonic.net/apache/tomee/tomee-8.0.0/apache-tomee-8.0.0-plume.tar.gz
    and unpack it on the Desktop
    cd ~/Downloads
    tar -xvzf apache-tomee-8.0.0-plume.tar.gz -C ~/Desktop
or go to 
    https://tomee.apache.org/download-ng.html and download the TAR.GZ version of TomEE plume
    Firefox will ask you what to do - either 
        Save File and then unpack it or
        Open with Archive Manager: unpack to the Desktop

2. Create admin user (once)

TomEE works pretty much like the standard Tomcat, so you have to create users with different profiles.
To make life easier we create one super user with all the privileges we need
To do this we need to edit one text file (vi or the TextEditor app - your choice)

This file is 
    ~Desktop/apache-tomee-plume-8.0.0/conf/tomcat-users.xml

Before the last line </tomcat-users> add
  <user username="cop4856" password="mytomEE" roles="manager-gui, manager-script, admin"/>

So the end of the file reads:
  ...
  <!-- Activate those lines to get access to TomEE GUI if added (tomee-webaccess) -->
  <!--
  <role rolename="tomee-admin" />
  <user username="tomee" password="tomee" roles="tomee-admin,manager-gui" />
  -->
  <user username="cop4856" password="mytomEE" roles="manager-gui, manager-script, admin"/>
</tomcat-users>

That allows the user cop4856 with this password to run manager functions from the web interface,
from a script connection (such as Netbeans) and to generally configure the server


3. Start TomEE (every time)

Run a command prompt in ~/Desktop/apache-tomee-plume-8.0.0/bin

./startup.sh

That's it!

Open a browser and go to http://localhost:8080/ - there's the TomEE console


4. Add TomEE to Netbeans (once)

a. In the Services tab, right click on "Servers" --> Add Server...
b. Select "Apache Tomcat or TomEE"
c. In "Server Location" browse to ~/Desktop/apache-tomee-plume-8.0.0/ (the top directory)
d. Enter user "cop4856" and password "mytomEE"

Once you're through with this dialog, the server is known.


5. Define the server for the MultiDOServlet

a. Right click on the "MultiDOServlet" root in the Project tab
b. Select "Properties" --> "Run" under "Categories" on the left
c. Select "Apache Tomcat or TomEE"


6. Rebuild the MultiDOServlet project

a. Right click on the "MultiDOServlet" root in the Project tab --> "Build with Dependencies"
a. Right click on the "MultiDOServlet" root in the Project tab --> "Run"