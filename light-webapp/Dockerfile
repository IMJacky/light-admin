FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 10003
ENTRYPOINT ["java","-Duser.timezone=GMT+8","-jar","/app.jar"]