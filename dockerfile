FROM openjdk:13-jdk
ADD target/parser-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]
