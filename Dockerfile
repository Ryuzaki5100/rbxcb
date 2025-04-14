# Build stage
FROM maven:3.9.9-eclipse-temurin-17 AS java-build
WORKDIR /app
COPY demo/pom.xml .
COPY demo/src ./src
COPY demo/.mvn ./.mvn
COPY demo/mvnw .
COPY demo/mvnw.cmd .
RUN chmod +x ./mvnw
RUN ./mvnw clean install -e -X
RUN ls -la target/

# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=java-build /app/target/demo-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENV JAVA_OPTS="-Xms512m -Xmx1024m"

CMD ["java", "-jar", "/app/app.jar"]