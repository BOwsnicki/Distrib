
This is also the template for your Project 4:

It looks a lot like that Tomcat is the better choice to deploy and run JDBC based services than Payara

1. Download TomEE plume from https://tomee.apache.org/download-ng.html (for the VM pick the TAR.GZ version)

2. Open it with the Archive Manager and extract it to the desktop

3. Copy the provided file "tomcat-users.xml" into the "conf" directory of the TomEE installation, like
   /home/cop4856/Desktop/apache-tomee-plume-8.0.0/conf. Make a backup of the original. 
   This creates a user "cop4856" with password "mytomee"

4. Open a terminal in /home/cop4856/Desktop/apache-tomee-plume-8.0.0/bin and enter 
   ./startup.sh - it will return immediately, but it takes about 45 seconds until the server is there

5. Open a browser at localhost:8080, look around. Then click on the "Manager App" button and enter the credentials from 3.

6. You should see a couple of predefined apps, but that's about it for the moment

7. In the "Services" tab in Netbeans, right click on "Servers" and select "Add Server...", select "Tomcat or TomEE"

8. In "Server Location" navigate to the top directory of your TomEE installation /home/cop4856/Desktop/apache-tomee-plume-8.0.0

9. For "User Name" and "Password" supply the credentials from 3.

10. In your Project, right click the project root node, select "Properties", then "Run"

11. In the "Server:" field select "Tomcat or TomEE", click "Ok"

12. Do that again and try to see if your project name appears in the "Context Path:" field. 
    That could be hard to see, since NB 11 has a glitch there.
    Type it in, if not.

13. To deploy your program 
        make sure the web.xml (under Web Pages --> META-INF) is showing your start page
        click "Build with Dependencies"
        click "Run"


