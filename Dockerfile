# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
# Download dependencies first (cached layer)
RUN mvn dependency:go-offline -B
COPY src ./src
# Build the WAR file
RUN mvn clean package -DskipTests

# Stage 2: Deploy the WAR file to Tomcat 10
FROM tomcat:10.1-jdk21
WORKDIR /usr/local/tomcat

# Remove default ROOT application to deploy at root URL
RUN rm -rf webapps/ROOT

# Copy the built WAR file from the build stage to webapps/ROOT.war
COPY --from=build /app/target/FashionStore.war webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
