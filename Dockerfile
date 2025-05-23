FROM eclipse-temurin:21-jdk-alpine-3.21

ARG JAR_FILE=./target/cst-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} application.jar

ENTRYPOINT ["java", "-jar", "application.jar"]

