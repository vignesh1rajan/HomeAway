# HomeAway
Automation Coding Exercise 

Getting Started:
    
    Pre-requisites- 
      The environment must contain git, maven, firefox and java in order to run the tests successfully
    
    Installation from commandline -
      1. Create a directory to store project
      2. Clone from git-hub: git clone https://github.com/vignesh1rajan/HomeAway.git
      3. CD into the directory from above and pull dependencies and run tests
          mvn clean install -DsuiteXmlFile=Resources/RegressionTest.xml
    
    Running from IDE-
      1. Import project as a maven project
      2. Get TestNG if it is not already installed
      3. Create a TestNG configuration by "SuiteName" and choose any of .xml tests from "Resources/" folder. (RegressionTest.xml contains all the tests)
     
     
  Notes:
    In order to keeps tests from failing move curser away from browser area (task bar).
    
  Issues:
    There are many instances where locators "xpath" where used in order to find objects. This is very unreliable and hard to maintain. Best practices include having a unique id's for interactive objects. Naming conventions for classname and id's needs to be consistent and meaningfull. 
   
   Tools: 
   
      https://git-scm.com/downloads
      https://maven.apache.org/download.cgi
      https://maven.apache.org/install.html
      https://www.mozilla.org/en-US/firefox/all/
