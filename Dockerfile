FROM adoptopenjdk/openjdk11:alpine-jre

ADD target/*.jar /usr/share/app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/usr/share/app.jar"]
