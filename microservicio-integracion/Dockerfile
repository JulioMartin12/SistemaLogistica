# Usa una imagen base robusta compatible con Java 17
FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.additional-location=/app/config/"]
