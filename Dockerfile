# Etapa 1: Construcción
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar archivos de configuración Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargar dependencias (optimiza el cache de Docker)
RUN mvn dependency:go-offline -B || true

# Copiar código fuente
COPY src ./src

# Compilar aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto
EXPOSE 8081

# Variables de entorno por defecto (pueden ser sobrescritas)
ENV SPRING_PROFILES_ACTIVE=default

# Ejecutar aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
