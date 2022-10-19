FROM amazoncorretto:8
ADD target/*.jar Bayztracker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","Bayztracker.jar"]