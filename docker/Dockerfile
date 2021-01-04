FROM openjdk:15-alpine
ARG JAR_FILE=/build/libs/PassnetRecruitment.jar
COPY ${JAR_FILE} app.jar
COPY /src/main/resources/*.yml ./profiles
ENTRYPOINT ["java","-jar", "/app.jar", "--spring.profiles.active=prod", "--spring.config.location=./profiles"]