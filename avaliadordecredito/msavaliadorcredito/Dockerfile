FROM maven:3-amazoncorretto-21 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -Dskiptests

FROM openjdk:21-ea AS app
WORKDIR /app
COPY --from=build ./build/target/*.jar ./msclientes.jar

ARG RABBITMQ_SERVER=rabbitmq
ARG EUREKA_SERVER=eureka-server
ARG EUREKA_USER=eureka-user
ARG EUREKA_PASSWORD=aismdoiam12

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "msclientes.jar"]
