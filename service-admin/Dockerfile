#utiliser la version 17 de java
FROM openjdk:17
#alloue espace
VOLUME /tmp
#recuperer executable du servic de config
ADD target/service-admin.jar service-admin.jar 
#pour lancer le projet
ENTRYPOINT [ "java","-jar","/service-admin.jar" ]