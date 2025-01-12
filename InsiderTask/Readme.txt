ConfigReader reads config.properties and retrieves configuration variables.  
DriverFactory sets up the web drivers and handles driver-related configurations.  
Helper provides a method to capture screenshots.  
TestWatcherExtension works with the Helper class for to automatically capture a screenshot when a test fails.  

All utility methods and page object classes are located in main/java/Test.Pages and main/java/utils
Main test steps and test cases are located in test/java/Tests.
Variables at config.properties
The project can be executed by running the following command in the terminal: mvn test -Dbrowser=firefox , mvn clean test 
