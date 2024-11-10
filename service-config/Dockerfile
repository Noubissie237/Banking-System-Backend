FROM openjdk:17
VOLUME /tmp
ADD target/service-config-0.0.1-SNAPSHOT.jar service-config.jar
EXPOSE 2370
ENTRYPOINT [ "java", "-jar", "/service-config.jar", "--server.port=2370" ]