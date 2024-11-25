# Étape 1 : Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copier uniquement le pom.xml pour gérer les dépendances
COPY pom.xml .
RUN mvn dependency:resolve

# Copier le reste du code source et construire le projet
COPY src ./src
RUN mvn clean install -DskipTests

# Étape 2 : Runtime
FROM openjdk:17
WORKDIR /app

# Copier uniquement le JAR généré depuis l'étape précédente
COPY --from=build /app/target/service-users-0.0.1-SNAPSHOT.jar service-users.jar

# Variables d'environnement
ENV SPRING_APPLICATION_NAME=service-users
ENV SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/Noubissie237/banking-system-cloud-conf

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "/app/service-users.jar"]
