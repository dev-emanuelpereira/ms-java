FROM maven:3-amazoncorretto-21 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -Dskiptests

FROM openjdk:21-ea AS app
WORKDIR /app
COPY --from=build ./build/target/*.jar ./eurekaserver.jar
EXPOSE 8761
ENTRYPOINT java -jar eurekaserver.jar
