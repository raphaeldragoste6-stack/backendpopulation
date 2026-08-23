# Étape 1 : Build de l'application avec JDK 17
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# Copie de l'ensemble des fichiers
COPY . .

# Donner les droits d'exécution au wrapper Maven
RUN chmod +x mvnw

# Compilation sans exécuter les tests unitaires
RUN ./mvnw clean package -DskipTests

# Étape 2 : Image d'exécution légère avec JRE 17
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Récupération du fichier JAR généré à l'étape précédente
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]