FROM maven:3-jdk-11 AS build
COPY src /usr/src/app/
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:11-slim
COPY --from=build /usr/src/app/target/proxy-*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]