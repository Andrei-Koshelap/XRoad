FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/cadastre-rest.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "app.jar"]
