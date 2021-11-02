FROM openjdk:11-jre-slim as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9198
ENTRYPOINT ["java","-jar","/app.jar"]
