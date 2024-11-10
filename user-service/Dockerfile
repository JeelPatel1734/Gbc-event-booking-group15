# Base image for Gradle build
FROM gradle:8-jdk21-alpine AS build

# Set the working directory
WORKDIR /home/gradle/src

# Copy project files to the container
COPY . /home/gradle/src

# Build the project (skip tests for faster build if necessary)
RUN gradle build -x test

# Runtime image
FROM openjdk:21-jdk

# Create a directory for the app
RUN mkdir /app

# Copy the built JAR file to the runtime image
COPY --from=build /home/gradle/src/build/libs/*.jar /app/user-service.jar

# Set environment variables
ENV POSTGRES_DB=user-service \
    POSTGRES_USER=admin \
    POSTGRES_PASSWORD=password

# Expose the port
EXPOSE 8087

# Start the application
CMD ["java", "-jar", "/app/user-service.jar"]
