FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

COPY pom.xml .
COPY statistics-service/pom.xml service/pom.xml
COPY statistics-service/src service/src
COPY statistics-service/mvnw service/
COPY statistics-service/.mvn service/.mvn

RUN cd service && chmod +x ./mvnw && ./mvnw clean package -DskipTests -DskipITs

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /app/service/target/statistics-service-0.0.1-SNAPSHOT.jar ./statistics-service-0.0.1-SNAPSHOT.jar

EXPOSE 9081

CMD ["java", "-jar", "statistics-service-0.0.1-SNAPSHOT.jar"]