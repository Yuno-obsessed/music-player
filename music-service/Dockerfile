FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY music-service/pom.xml service/pom.xml
COPY music-service/src service/src
COPY music-service/mvnw service/
COPY music-service/.mvn service/.mvn

RUN cd service && chmod +x ./mvnw && ./mvnw clean package -DskipTests -DskipITs

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/service/target/music-service-0.0.1-SNAPSHOT.jar ./music-service-0.0.1-SNAPSHOT.jar

EXPOSE 9080
EXPOSE 7080

CMD ["java", "-jar", "music-service-0.0.1-SNAPSHOT.jar"]