# airalo-task Instructions

- Have environment with:
> - Java 11 JDK ([jdk11 download](https://adoptium.net/temurin/releases/?os=windows&arch=x64&package=jdk&version=11), select option to set JAVA_HOME)
> - Maven installed and added to the Path. To verify if you have maven installed you can run "***mvn --version***" command ([instruction to install maven](https://maven.apache.org/install.html), [download page](https://maven.apache.org/download.cgi))
> - Have browser installed in the system, according to value in the environment.properties file (by default - Chrome)
- Clone the [repository](https://github.com/ispitkovskyi/airalo-task.git)
- Open CMD/terminal session, navigate into the repository root directory.
- Run the "**run.bat**" file (or just run the **mvn surefire-report:report** from the cmd, while being in the project root directory). Project should be compile and tests executed.
- Navigate into the "<project_root>\target\site" directory, open the **surefire-report.html**