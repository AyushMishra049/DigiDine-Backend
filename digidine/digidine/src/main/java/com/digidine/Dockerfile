# Use Maven + Java 17 image
FROM maven:3.9.9-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Move into the actual Spring Boot project folder
WORKDIR /app/digidine

# Build the Spring Boot app
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 10000

# Run the jar
CMD ["java", "-jar", "target/digidine-0.0.1-SNAPSHOT.jar"]