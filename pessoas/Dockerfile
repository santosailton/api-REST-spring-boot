# Use the official OpenJDK 17 as the base image
FROM openjdk:17-jdk-alpine3.14

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/pessoas-0.0.1-SNAPSHOT.jar /app/

# Expose the default port for the application
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "pessoas-0.0.1-SNAPSHOT.jar"]

