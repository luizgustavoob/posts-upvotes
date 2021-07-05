FROM    maven:3-openjdk-8-slim AS builder
WORKDIR /build/
COPY    . .
RUN     mvn clean package

FROM    openjdk:8-jre-slim-buster
WORKDIR /app/
COPY    --from=builder /build/target/*.jar api.jar
CMD     ["java", "-jar", "api.jar"]