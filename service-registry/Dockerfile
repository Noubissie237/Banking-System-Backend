#utiliser la version 17 de java
FROM openjdk:17
#alloue espace
VOLUME /tmp
#recuperer executable du servic de config
ADD target/service-registry-0.0.1-SNAPSHOT.jar service-registry-0.0.1-SNAPSHOT.jar 
#pour lancer le projet
ENTRYPOINT [ "java","-jar","/service-registry-0.0.1-SNAPSHOT.jar" ]