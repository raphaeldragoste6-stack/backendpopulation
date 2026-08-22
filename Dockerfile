# Utiliser une image Java officielle
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copier les fichiers du projet
COPY . .

# Accorder les permissions d'exécution au wrapper Maven
RUN chmod +x mvnw

# Compiler le projet avec le wrapper ./mvnw au lieu de mvn
RUN ./mvnw clean package -DskipTests

# Deuxième étape : Exécution
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]