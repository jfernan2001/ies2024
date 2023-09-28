

# Server - docker images et al.
## Apache Tomcat

version 8 
https://www.cprime.com/resources/blog/deploying-your-first-web-app-to-tomcat-on-docker/

```
FROM tomcat:8.0-alpine
LABEL maintainer=”deepak@softwareyoga.com”
ADD sample.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD [“catalina.sh”, “run”]
```

docker offical 
https://hub.docker.com/_/tomcat

"(...)
How to use this image.
Note: as of docker-library/tomcat#181, the upstream-provided (example) webapps are not enabled by default, per upstream's security recommendations, but are still available under the webapps.dist folder within the image to make them easier to re-enable.

Run the default Tomcat server (CMD ["catalina.sh", "run"]):

```
$ docker run -it --rm tomcat:9.0
```

You can test it by visiting http://container-ip:8080 in a browser or, if you need access outside the host, on port 8888:

```
$ docker run -it --rm -p 8888:8080 tomcat:9.0
```

from 2019
https://medium.com/@pra4mesh/deploy-war-in-docker-tomcat-container-b52a3baea448

	using tomcat 8.0 

from 2023  - from zero
https://cloudinfrastructureservices.co.uk/how-to-create-a-tomcat-docker-container-docker-tomcat-image/

from 2022
Deploying a Java War in a Docker Container
https://www.baeldung.com/docker-deploy-java-war

"...WAR (Web Application Archive) is a zipped archive file that packages all the web application-related files and their directory structure.

To make things simple, deploying a WAR file on Tomcat is nothing but copying that WAR file into the deployment directory of the Tomcat server. The deployment directory in Linux is $CATALINA_HOME/webapps. $CATALINA_HOME denotes the installation directory of the Tomcat server.

After this, we need to restart the Tomcat server, which will extract the WAR file inside the deployment directory.
..."

dockerfile...

```
FROM tomcat
COPY ROOT.war /usr/local/tomcat/webapps/
```

from 2021 ... not basic... multilevel build 
Dockerize a Spring Boot Application with Tomcat
https://www.indellient.com/blog/dockerize-a-spring-boot-application-with-tomcat/

## RedHat WildFly

from 2022   - - deploy war ...
Creating custom WildFly Images for Docker
https://www.mastertheboss.com/soa-cloud/docker/deploying-applications-on-your-docker-wildfly-image/

```
FROM quay.io/wildfly/wildfly
ADD helloworld.war /opt/jboss/wildfly/standalone/deployments/
```

https://hub.docker.com/r/jboss/wildfly/

from 2022  ... advanced... not trivial.
Use the wildfly-maven-plugin to create a Docker image of your application
https://www.wildfly.org/news/2022/08/04/wildfly-maven-docker/

## Payara, 

https://www.payara.fish/downloads/payara-docker-images/

https://hub.docker.com/r/payara/server-web

```
docker run -p 8080:8080 payara/server-web
```

MUST MUST 
https://docs.payara.fish/community/docs/documentation/ecosystem/docker-images/server-image-overview.html

exposed ports 
8080 - HTTP listener
8181 - HTTPS listener
4848 - Admin Service HTTPS listener
9009 - JWDP Debug port

### Application Deployment

$DEPLOY_DIR directory (which defaults to /opt/payara/deployments) for files and sub-folders and deploy them automatically after the domain is started.

Any RAR files artefacts found in the directory will always be deployed first


### Deploy Applications using a Custom Image

```
FROM payara/server-full
COPY myapplication.war $DEPLOY_DIR
```

And to launch the container, simply build and run the image like this:

```
docker build -t mycompany/myapplication:1.0 .
docker run -p 8080:8080 mycompany/myapplication:1.0
```

## Glassfish ...
 
 under construction


# server - war

under construction

# servlets

from 2023
Guide to Java Servlets
https://howtodoinjava.com/java/servlets/complete-java-servlets-tutorial/#webservlet_annotation

https://central.sonatype.com/artifact/javax.servlet/javax.servlet-api/3.0.1?smo=true

Hello Servlet Example using Maven
https://javatrainingschool.com/first-servlet-example/

```
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <scope>provided</scope>
</dependency>
```

Apache Maven WAR Plugin/ Usage Edit
https://maven.apache.org/plugins/maven-war-plugin/usage.html

```
(...)
<project>
  ...
  <groupId>com.example.projects</groupId>
  <artifactId>documentedproject</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>Documented Project</name>
  <url>http://example.com</url>
  ...
</project>
```

#  War 

Generate a WAR File in Maven
https://www.baeldung.com/maven-generate-war-file


```
(...)
<plugin>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.4.0</version>
</plugin>
```


from 2023 ....  NICE ---- assume tomcat installed 
Creating and Deploying Java Web Applications using Maven and Tomcat
https://medium.com/@AlexanderObregon/creating-and-deploying-java-web-applications-using-maven-and-tomcat-d5cb9a81824a

mount disk in local 

https://gist.github.com/zjor/bbabcf567e9c06f4a327


```
docker run -it --rm -p 8888:7080 \
  -v /${HOST_PATH}/webapps:/usr/local/tomcat/webapps \
  -v /${HOST_PATH}/server.xml:/usr/local/tomcat/conf/server.xml \
  tomcat:8.0
```  
  
  
... local example

```  
docker run -it --rm -p 8888:7080   -v C:\ies\webapps\webapps:/usr/local/tomcat/webapps   tomcat:8.0
```  
  
example - servlet 
   
```  
  mvn archetype:generate -DgroupId=com.example -DartifactId=my-webapp -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
 ``` 
  
  https://medium.com/@AlexanderObregon/creating-and-deploying-java-web-applications-using-maven-and-tomcat-d5cb9a81824a
  

https://medium.com/@AlexanderObregon/creating-and-deploying-java-web-applications-using-maven-and-tomcat-d5cb9a81824a
first-servlet-example  


http://localhost:8080/first-servlet-example/hello

## Changing Tomcat HTTP Port to 80

https://www.baeldung.com/tomcat-change-port#:~:text=By%20default%2C%20Apache%20Tomcat%20runs%20on%20port%208080.


```
docker run -it --rm -p 8080:8080   -v C:\ies\webapps:/usr/local/tomcat/webapps  tomcat:9.0.59-jdk11
``` 
 
# spring boot --> war

under construction 

# server for running in docker - mouting instalation dir 

## wildfly


 - from 2022   -  - deploy war ...
Creating custom WildFly Images for Docker
https://www.mastertheboss.com/soa-cloud/docker/deploying-applications-on-your-docker-wildfly-image/

``` 
FROM quay.io/wildfly/wildfly
ADD helloworld.war /opt/jboss/wildfly/standalone/deployments/
``` 

## tomcat 

- from 2022- 
Deploying a Java War in a Docker Container
https://www.baeldung.com/docker-deploy-java-war


"...WAR (Web Application Archive) is a zipped archive file that packages all the web application-related files and their directory structure.

To make things simple, deploying a WAR file on Tomcat is nothing but copying that WAR file into the deployment directory of the Tomcat server. The deployment directory in Linux is $CATALINA_HOME/webapps. $CATALINA_HOME denotes the installation directory of the Tomcat server.

After this, we need to restart the Tomcat server, which will extract the WAR file inside the deployment directory.
..."

dockerfile...

``` 
FROM tomcat
COPY ROOT.war /usr/local/tomcat/webapps/
``` 

## payara
 --  nota: existe imagem web ( i.e. not full )

https://www.payara.fish/downloads/payara-docker-images/
https://hub.docker.com/r/payara/server-web

``` 
docker run -p 8080:8080 payara/server-web
``` 

https://docs.payara.fish/community/docs/documentation/ecosystem/docker-images/server-image-overview.html


``` 
FROM payara/server-full
COPY myapplication.war $DEPLOY_DIR
``` 

And to launch the container, simply build and run the image like this:

``` 
docker build -t mycompany/myapplication:1.0 .
docker run -p 8080:8080 mycompany/myapplication:1.0
``` 

## web app com tomcat a correr em docker 

link com web app - assume tomcat instalado mas abaixo sugiro montar o webapps ( directorio para instalação ) e funciona

--> link  ( apr 2023 ) 

Creating and Deploying Java Web Applications using Maven and Tomcat
https://medium.com/@AlexanderObregon/creating-and-deploying-java-web-applications-using-maven-and-tomcat-d5cb9a81824a

--> docker

``` 
 docker run -it --rm -p 8080:8080   -v C:\ies\webapps:/usr/local/tomcat/webapps  tomcat:9.0.59-jdk11
 ``` 
 
 