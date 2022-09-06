FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} patientmodule.jar
ENTRYPOINT ["java","-jar","/patientmodule.jar"]