#FROM openjdk:17-jdk
FROM amazoncorretto:17-alpine3.17
VOLUME /tmp
ARG JAR_VERSION
RUN echo "JAR VERSION: $JAR_VERSION"
# COPY build/libs/g0tcha-g0tcha-doggy-${JAR_VERSION}.jar app.jar
COPY gradle/wrapper/gradle-wrapper.jar app.jar
CMD ["sh","-c","java -jar app.jar"]