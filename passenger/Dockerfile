FROM openjdk:11.0.16-jre
VOLUME /tmp
COPY target/passenger-*.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]
