# Stage 1: Build the application
FROM gradle:jdk20-alpine as builder
WORKDIR /app
COPY build.gradle .
COPY src src
RUN gradle clean build --no-daemon

# Stage 2: Create a minimal JRE image
FROM amazoncorretto:20.0.2-alpine3.18
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "app.jar"]