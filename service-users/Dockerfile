<<<<<<< HEAD
#utiliser la version 17 de java
FROM openjdk:17
#alloue espace
VOLUME /tmp
#recuperer executable du servic de config
ADD target/service-users-0.0.1-SNAPSHOT.jar service-users-0.0.1-SNAPSHOT.jar 
#pour lancer le projet
ENTRYPOINT [ "java","-jar","/service-users-0.0.1-SNAPSHOT.jar" ]
=======
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/service-users-0.0.1-SNAPSHOT.jar service-users.jar

ENV SPRING_APPLICATION_NAME=service-users
ENV SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/Noubissie237/banking-system-cloud-conf

ENTRYPOINT ["java", "-jar", "/app/service-users.jar"]
>>>>>>> fd0a6e4972a24b6ff3ae230fd0fe6dfc1776bc0e
