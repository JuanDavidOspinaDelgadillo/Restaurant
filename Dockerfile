FROM eclipse-temurin:21

LABEL author=group5

COPY target/Restaurant-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]