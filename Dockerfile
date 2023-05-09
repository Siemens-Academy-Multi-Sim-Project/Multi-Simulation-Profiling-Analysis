FROM eclipse-temurin:17

WORKDIR /app

VOLUME /app

EXPOSE 8080

# RUN ["./gradlew", "clean", "build", "-x", "test"]
# COPY /build/libs/Multi-Simulation-Profiling-Analysis-0.0.1-SNAPSHOT.war backend.war
# RUN apt-get update && apt-get install -y --no-install-recommends \
#   && apt-get install -y maildev 

CMD ["chmod" "+x" "./entrypoint.sh"]
ENTRYPOINT ["./entrypoint.sh"]
CMD ["java", "-jar", "backend.war"]