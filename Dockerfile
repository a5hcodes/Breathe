# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the project files
COPY . /app

# Build the Spring Boot application (Assuming you're using Maven)
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/*.jar"]