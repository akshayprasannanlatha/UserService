FROM openjdk:11.0-jdk-slim
VOLUME /tmp
COPY /target/user-service-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8811
ENV JAVA_OPTS=""
RUN sh -c "touch user-service-0.0.1-SNAPSHOT.jar"
ENTRYPOINT [ "java", "-jar", "user-service-0.0.1-SNAPSHOT.jar" ]
# docker file to create docker image for user-service
