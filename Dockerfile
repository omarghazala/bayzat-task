FROM gradle:7.3.3-jdk17 AS gradlebuilder-clean
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN ./gradlew bootJar -DskipTests


FROM azul/zulu-openjdk-alpine:17-jre
RUN mkdir /app
COPY --from=gradlebuilder-clean /project/build/libs/*.jar /app/bayzdelivery-0.0.1-SNAPSHOT.jar
WORKDIR /app
CMD ["java", "-jar", "bayzdelivery-0.0.1-SNAPSHOT.jar"]
