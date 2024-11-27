FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/service-config-0.0.1-SNAPSHOT.jar service-config.jar

ENV SPRING_APPLICATION_NAME=service-config
ENV SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/Noubissie237/banking-system-cloud-conf

EXPOSE 2370

ENTRYPOINT ["java", "-jar", "/app/service-config.jar"]
