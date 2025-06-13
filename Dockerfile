# Use an official Tomcat base image
FROM tomcat:9.0-jdk17-openjdk

# Set working directory
WORKDIR /usr/local/tomcat/webapps/

# Copy the built WAR file into Tomcat
COPY target/maven-web-application.war /usr/local/tomcat/webapps/maven-web-application.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
