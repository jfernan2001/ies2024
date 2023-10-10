

Some notes:

# deploying war in a tomcat 

## mounting local dir in the webapps tomcat directory 

An easy way to deploy a generic tomcat with shared mounted directory to deploy webapps ( *.war)


	docker run -it --rm -p 9999:8080   -v C:\ies\webapps:/usr/local/tomcat/webapps   tomcat:8.5.69-jdk8-openjdk


By default tomcat assumes the war are deployed in usr/local/tomcat/webapps. Mounting a local directory ( C:\ies\webapps in the example ) enables you to deploy webapps only by copy war to  local C:\ies\webapps - implicitly installing it in tomcat running in docker

## create dockerfile 

1. Select proper tomcat docker image ( tomcat in example)
2. copy the war to the  webapps directory  ( ROOT.war in the example )

Can use a Dockerfile to do this when deploying your application

Dockerfile
'''
FROM tomcat
COPY ROOT.war /usr/local/tomcat/webapps/
'''

Other option - in here you need to know “catalina.sh” “run” is starting the tomcat.

FROM tomcat:8.0-alpine
LABEL maintainer=”deepak@softwareyoga.com”
ADD sample.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD [“catalina.sh”, “run”]


# deploying  with dedicated dockerfile


from 2022   - - deploy war ...
Creating custom WildFly Images for Docker
https://www.mastertheboss.com/soa-cloud/docker/deploying-applications-on-your-docker-wildfly-image/

Dockerfile
'''
FROM quay.io/wildfly/wildfly
ADD helloworld.war /opt/jboss/wildfly/standalone/deployments/
'''

# deploy in payara 


Can select payara images - 
https://www.payara.fish/downloads/payara-docker-images/
https://hub.docker.com/r/payara/server-web


docker run -p 8080:8080 payara/server-web

https://docs.payara.fish/community/docs/documentation/ecosystem/docker-images/server-image-overview.html

exposed ports 
8080 - HTTP listener
8181 - HTTPS listener
4848 - Admin Service HTTPS listener
9009 - JWDP Debug port



## using the deployment directory 

$DEPLOY_DIR directory (which defaults to /opt/payara/deployments) for files and sub-folders and deploy them automatically after the domain is started.

Any RAR files artefacts found in the directory will always be deployed first


Deploy Applications using a Custom Image

Dockerfile
'''
FROM payara/server-full
COPY myapplication.war $DEPLOY_DIR
'''

## deploying  with dedicated dockerfile

Dockerfile
'''
FROM payara/server-full
COPY myapplication.war $DEPLOY_DIR
'''

And to launch the container, simply build and run the image like this:

docker build -t mycompany/myapplication:1.0 .
docker run -p 8080:8080 mycompany/myapplication:1.0




# possible errors


´´´
Unsupported major.minor version 52.0 (unable to load class) [duplicate]
´´´
https://stackoverflow.com/questions/42820521/unsupported-major-minor-version-52-0-unable-to-load-class

The problems is due ( in short) to different java version in development and the deployment environment

In case of the tomcat some 8.x version / images depends on jdk1.7 while most dev environment work with java 1.8 or higher

see 

How to Fix java.lang.UnsupportedClassVersionError
https://www.baeldung.com/java-lang-unsupportedclassversion


One option was to place 1.8 as target for the webapps 

in pom.xml
'''
 <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>

'''
and make sure the tomcat version was compliant with 1.8 or highr



For running a web apps compiled under jdk1.8  you can use for instance 

tomcat:8.5.69-jdk8-openjdk  

Look at https://hub.docker.com/_/tomcat for full docker images




