# Use official Java 21 JDK image
FROM eclipse-temurin:21-jdk-alpine

# Metadata
LABEL maintainer="yourname@example.com"

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY target/*.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 11000

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
