FROM openjdk:17
VOLUME /tmp
ADD target/service-config-0.0.1-SNAPSHOT.jar service-config.jar
ENTRYPOINT [ "java", "-jar", "/service-config.jar" ]