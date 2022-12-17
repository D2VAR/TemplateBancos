FROM openjdk:17
COPY target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar", "--port=8081"]