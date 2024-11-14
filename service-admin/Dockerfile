#utiliser la version 17 de java
FROM openjdk:17
#alloue espace
VOLUME /tmp
#recuperer executable du servic de config
ADD target/service-admin.jar service-admin.jar 
#pour lancer le projet
ENTRYPOINT [ "java","-jar","/service-admin.jar" ]
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/service-admin-0.0.1-SNAPSHOT.jar service-admin.jar

ENV SPRING_APPLICATION_NAME=service-admin
ENV SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/Noubissie237/banking-system-cloud-conf

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "/app/service-admin.jar"]
