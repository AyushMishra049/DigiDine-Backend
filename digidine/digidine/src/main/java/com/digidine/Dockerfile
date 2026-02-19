# Use Maven + Java 17 image
FROM maven:3.9.9-eclipse-temurin-17

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the Spring Boot application
RUN mvn clean package -DskipTests

# Expose port (Render internal port)
EXPOSE 10000

# Run the generated JAR
CMD ["java", "-jar", "target/*.jar"]

