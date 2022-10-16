FROM amazoncorretto:8
COPY target/*.jar BayzTracker.jar
ENTRYPOINT ["java","-jar","/BayzTracker.jar"]