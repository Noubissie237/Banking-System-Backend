FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src
RUN mvn clean install -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/service-transactions-0.0.1-SNAPSHOT.jar service-transactions.jar

ENV SPRING_APPLICATION_NAME=service-transactions
ENV SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/Noubissie237/banking-system-cloud-conf

ENTRYPOINT ["java", "-jar", "/app/service-transactions.jar"]
