FROM gradle:jdk17

WORKDIR /app

COPY build.gradle .
COPY settings.gradle .
COPY gradlew .

COPY src src

EXPOSE 8080

CMD gradle bootRun

# RUN ./gradlew build


# EXPOSE 8080


# CMD ["java", "-jar", "build/libs/user-0.0.1-SNAPSHOT.jar"]