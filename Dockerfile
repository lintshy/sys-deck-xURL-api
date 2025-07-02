FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/xURL-api-1.0.0.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "xURL-api-1.0.0.jar"]
