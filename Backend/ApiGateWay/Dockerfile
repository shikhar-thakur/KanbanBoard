FROM openjdk:11-jre-slim
WORKDIR usr/src
EXPOSE 9000
ADD ./target/ApiGateWay-0.0.1-SNAPSHOT.jar /usr/src/ApiGateWay-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ApiGateWay-0.0.1-SNAPSHOT.jar"]