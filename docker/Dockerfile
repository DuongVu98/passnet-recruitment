FROM openjdk:15-alpine
ARG JAR_FILE=/build/libs/PassnetRecruitment.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar", "--spring.profiles.active=${PROFILE}"]