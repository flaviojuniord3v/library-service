FROM adoptopenjdk:11-jre-hotspot as builder
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} library-service.jar
ENTRYPOINT ["java","-jar","/library-service.jar"]
