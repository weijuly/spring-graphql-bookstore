# NOTE:
#   1. The docker image will be built and tagged automatically by the maven plugin
#   2. Do not build the docker image manually, as it might lead to problems
FROM openjdk:8-jdk-alpine
ARG APP_JAR
COPY target/${APP_JAR} application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]
