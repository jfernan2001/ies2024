#install the project in local maven repository 

...
mvn install:install-file -Dfile=C:\ies\ies2024\lab01\prj1\target\prj1-1.0-SNAPSHOT.jar -DpomFile=C:\ies\ies2024\lab01\prj1\pom.xml       
...

# local dependencies in maven projects

https://medium.com/javarevisited/how-do-i-add-a-project-as-a-dependency-of-another-project-using-maven-4508283c01d1

"When this program is built using the mvn clean install command, it will generate a .jar file that will be stored in the local maven repository(~/.m2)."


  C:\Users\jfern\.m2\repository\ies\lab01\prj1\1.0-SNAPSHOT\prj1-1.0-SNAPSHOT.pom


... 
<dependency>
<groupId>ies.lab01</groupId>
<artifactId>prj1</artifactId>
<version>1.0-SNAPSHOT</version>
</dependency>
...




# some links on maven 

Where Is the Maven Local Repository?
https://www.baeldung.com/maven-local-repository   



need to check



Maven repository inside your project
https://medium.com/@jakubtutko/maven-repository-inside-your-project-4c55b4d73be8

deploy
... 
mvn deploy:deploy-file -Durl=c:\tmp\repo -Dfile=prj1-1.0-SNAPSHOT.jar -DgroupId=ies.lab01 -DartifactId=prj1 -Dpackaging=jar -Dversion=1.0
...

install 
... 
mvn install:install-file   -Dfile=prj1-1.0-SNAPSHOT.jar    -DgroupId=ies.lab01   -DartifactId=prj1   -Dversion=1.0    -Dpackaging=jar    -DlocalRepositoryPath=c:\tmp\repo
...



# md format 

https://www.freecodecamp.org/news/how-to-format-code-in-markdown/

https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax

