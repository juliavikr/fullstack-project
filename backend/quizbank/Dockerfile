# Use Maven to build the application
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use OpenJDK for a lightweight Java runtime
FROM openjdk:17-slim
COPY --from=build /app/target/quizbank-0.0.1-SNAPSHOT.jar quizbank.jar
ENTRYPOINT ["java","-jar","/quizbank.jar"]