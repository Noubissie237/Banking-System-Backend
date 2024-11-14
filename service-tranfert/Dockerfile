FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/service-tranfert-0.0.1-SNAPSHOT.jar service-tranfert.jar

ENV SPRING_APPLICATION_NAME=service-tranfert
ENV SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/Noubissie237/banking-system-cloud-conf

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/service-tranfert.jar"]
