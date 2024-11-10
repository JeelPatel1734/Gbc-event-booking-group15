# Base image
FROM ubuntu:latest
LABEL authors="jeelp"

ENTRYPOINT ["top", "-b"]

#----------------------------
# Build Stage
#----------------------------
FROM gradle:8-jdk21-alpine AS build

# Set the working directory
WORKDIR /home/gradle/src

# Copy Gradle and project files to the container
COPY . /home/gradle/src

# Run Gradle build
RUN gradle build -x test

#----------------------------
# Package Stage
#----------------------------
FROM openjdk:21-jdk

# Create a directory for the app
RUN mkdir /app

# Copy the jar to the production image from the builder stage
COPY --from=build /home/gradle/src/build/libs/*.jar /app/room-service.jar

# Set PostgreSQL database credentials as environment variables
ENV POSTGRES_DB=room-service \
    POSTGRES_USER=admin \
    POSTGRES_PASSWORD=password

# Expose the port the app runs on
EXPOSE 8083

# Command to run the app
ENTRYPOINT ["java","-jar","/app/room-service.jar"]
