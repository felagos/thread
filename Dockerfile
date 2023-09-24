# Stage 1: Build the application
FROM gradle:jdk20 as builder
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

# Add Spring Boot DevTools as a dependency
#RUN echo "dependencies { compileOnly 'org.springframework.boot:spring-boot-devtools' }" >> build.gradle

RUN gradle --stop

# Start the Spring Boot application with live reload
CMD ["java", "-Dspring.devtools.restart.enabled=false", "-jar", "app.jar", "--no-daemon", "--no-configure-on-demand"]
