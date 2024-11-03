
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/category-0.0.1-SNAPSHOT.jar category-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "category-service.jar"]