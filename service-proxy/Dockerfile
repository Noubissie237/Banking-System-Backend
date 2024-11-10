FROM openjdk:17
VOLUME /tmp
ADD target/service-proxy-0.0.1-SNAPSHOT.jar service-proxy.jar
ENTRYPOINT [ "java", "-jar", "/service-proxy.jar" ]