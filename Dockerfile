FROM openjdk:11
COPY ./target/video-service-1.0.jar video-service-1.0.jar
ENTRYPOINT ["java","-jar","/video-service-1.0.jar"]