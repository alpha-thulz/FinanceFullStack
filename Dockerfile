FROM node:22 AS frontend
LABEL authors="Thulani Tyaphile"

COPY frontend/package*.json ./
RUN npm install

COPY frontend/. ./
RUN npx vite build

FROM maven:3.9.8 AS backend
COPY backend/pom.xml pom.xml
COPY backend/src src
COPY --from=frontend dist/ src/main/resources/static
RUN mvn clean package -DskipTests

FROM eclipse-temurin
COPY --from=backend target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]