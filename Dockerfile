FROM openjdk:8-jdk-alpine
COPY /var/lib/jenkins/workspace/light-admin/light-webapp/target app.jar
EXPOSE 10003
ENTRYPOINT ["java","-jar","/app.jar"]