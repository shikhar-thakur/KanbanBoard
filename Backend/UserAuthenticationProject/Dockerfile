#here openjdk is docker image for Java 11, as in pom.xml Java version is 11
FROM openjdk
#creating a working directory inside the image
WORKDIR usr/src
EXPOSE 8085
#Copy executable jar file getting created inside target and add it in usr/lib working director
ADD ./target/UserAuthenticationProject-0.0.1-SNAPSHOT.jar /usr/src/UserAuthenticationProject-0.0.1-SNAPSHOT.jar
#Run the jar file
ENTRYPOINT ["java","-jar","UserAuthenticationProject-0.0.1-SNAPSHOT.jar"]