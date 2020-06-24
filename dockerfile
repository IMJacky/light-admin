FROM java:8

ENV TZ=Asia/Shanghai
ENV JVM_MEMORY 512M

COPY ./target/*.jar /tmp
RUN cp -f ./tmp/*.jar /app.jar

EXPOSE 10003

CMD echo "The application is starting..." && \
    java -Xmx${JVM_MEMORY} -jar /app.jar