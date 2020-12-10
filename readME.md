Prerequisites

Language:Java(v8+)

Test Runner:TestNg(v6.14+)
Rest Assured (4.3.2)
Build Tool:Maven(v3.6.3)
Maven Compiler Plugin(v3.1+)

###Import the Project
Create a directory on your machine.
Clone this repository into said directory.
Import the project into your IntelliJ (or IDE of your choice) as a Maven Project.
Click through the prompts, and confirm when it asks to Import from Sources

###Run a Maven Test

Run the following command to update any package dependencies:
  > $ mvn dependency:resolve.

Then run the following command to compile your test code:
  >   $ mvn test-compile 

Troubleshooting
To clean and install dependencies.

 >$ mvn dependency:purge-local-repository


   ## for running a specific test:
     mvn test -Dtest=testname 
     or right click and run each  API test 
  