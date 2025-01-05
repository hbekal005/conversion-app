# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project's JAR file into the container at /app
COPY target/conversion-app-0.0.1-SNAPSHOT.jar app.jar

# Build argument for the server port
ARG SERVER_PORT=8080

# Expose the port that the application will run on
EXPOSE ${SERVER_PORT}

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]