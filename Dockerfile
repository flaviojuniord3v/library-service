FROM amazoncorretto:11 as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} library-service.jar
ENTRYPOINT ["java","-jar","/library-service.jar"]
