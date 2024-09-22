FROM openjdk:21-slim

WORKDIR /app

COPY target/songapp-0.0.1.jar songapp.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "songapp.jar"]