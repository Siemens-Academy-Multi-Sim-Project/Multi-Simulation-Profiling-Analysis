FROM eclipse-temurin:17

WORKDIR /app

COPY target/Multi-Simulation-Profiling-0.0.1-SNAPSHOT.war /app/app.war

EXPOSE 8080

CMD ["java", "-jar", "app.war"]