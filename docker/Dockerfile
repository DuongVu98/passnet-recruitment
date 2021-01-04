FROM openjdk:8-jdk-alpine
ARG JAR_FILE=/build/libs/PassnetRecruitment.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]