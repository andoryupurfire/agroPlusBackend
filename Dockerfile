# ---------- Etapa 1: Construcción ----------
FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

# Copiamos solo el pom.xml primero (para aprovechar el cache de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Ahora copiamos el resto del código y compilamos
COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Etapa 2: Ejecución ----------
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copiamos SOLO el .jar ya construido desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]