FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk AS app
WORKDIR /app
COPY --from=build ./build/target/*.jar ./msclientes.jar
EXPOSE 8080
ARG KEYCLOAK_SERVER=keycloak
ARG KEYCLOAK_PORT=8081
ARG EUREKA_SERVER=eureka-server
ARG EUREKA_USER=eureka-user
ARG EUREKA_PASSWORD=aismdoiam12
ENTRYPOINT ["java", "-jar", "msclientes.jar"]
