# airalo-task Instructions

- Have environment with:
> - Java 11 JDK ([jdk11 download](https://adoptium.net/temurin/releases/?os=windows&arch=x64&package=jdk&version=11), select option to set JAVA_HOME)
> - Maven installed and added to the Path. To verify if you have maven installed you can run "***mvn --version***" command ([instruction to install maven](https://maven.apache.org/install.html), [download page](https://maven.apache.org/download.cgi))
> - Have browser installed in the system, according to value in the environment.properties file (by default - Chrome)
- Clone the [repository](https://github.com/ispitkovskyi/airalo-task.git)
- Open CMD/terminal session, navigate into the repository root directory.
- Run the "**run.bat**" file (or just run the **mvn surefire-report:report** from the cmd, while being in the project root directory). Project should be compile and tests executed.
- Navigate into the "<project_root>\target\site" directory, open the **surefire-report.html**


# Implementation approach
## UI "testJapaneseSimPackage" test
### "com/airalo/config"  package
1. DriverFactory - class responsible for instantiating driver.
2. EnvironmentProperties - class responsible for loading properties from the resources/environment.properties file
3. HttpClient - provides instance of http client
### "com/airalo/pages"  package
1. Page-object classes - responsible for interactions with web UI of https://www.airalo.com/
2. Each page class contains action-methods and assertion-methods relevant to respective page (element in page)
3. PageFactory used for lazy initialization of WebElement objects, using locators provided in @FindBy
### "com/airalo/tests/ui"  package
1. Test class / method

## REST "testMerhabaPackage" test
### "com/airalo/model"  package
1. entity-classes (DTO classes) which match data provided by the REST API in JSON responses
2. each JSON-response received after sending REST API request is parsed to create instance of matching entity-class
3. DTO objects provide more comprehensive, structured and conevenient way of data representation than raw JSON objects
### "com/airalo/api"  package
1. Service classes provide DSL-methods, which directly work with REST API
### "/com/airalo/tests/rest"  package
1. Test class / method

## Configuration
Configuration parameters are externalized to the **resources/environment.properties** file and are automatically loaded when tests are started.