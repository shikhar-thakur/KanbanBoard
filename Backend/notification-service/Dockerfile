    #here openjdk is docker image for Java 11, as in pom.xml Java version is 11
    FROM openjdk
    #creating a working directory inside the image
    WORKDIR usr/src
    #setting environment variable same name that are there in application.properties file
    ENV MONGO_DATABASE=NotificationDataBase
    ENV MONGO_URL=mongodb://localhost:27017/NotificationDataBase
    #Copy executable jar file getting created inside target and add it in usr/lib working director
    ADD ./target/NotificationService-0.0.1-SNAPSHOT.jar /usr/src/NotificationService-0.0.1-SNAPSHOT.jar
    #Run the jar file
    ENTRYPOINT ["java","-jar","NotificationService-0.0.1-SNAPSHOT.jar"]