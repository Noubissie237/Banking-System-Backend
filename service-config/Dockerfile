FROM openjdk:17
VOLUME /tmp
ADD target/service-config-0.0.1-SNAPSHOT.jar service-config.jar

ENV SPRING_APPLICATION_NAME=service-config
ENV SPRING_CLOUD_CONFIG_SERVER_GIT_URI=https://github.com/Noubissie237/banking-system-cloud-conf

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/service-config.jar"]
