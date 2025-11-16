# Base image: Eclipse Temurin JDK 17
FROM eclipse-temurin:17-jdk-focal

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Argumento para pasar el jar generado
ARG JAR_FILE=target/*.jar

# Copiar el jar construido
COPY ${JAR_FILE} app.jar


# Exponer puerto de Spring Boot
EXPOSE 8080

# Indicar a Spring Boot que use YAML externo
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.config.location=file:/app/config/application.yml"]
