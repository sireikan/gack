FROM openjdk:19

RUN microdnf install findutils

ENV APP_ROOT /app

COPY .. $APP_ROOT
WORKDIR $APP_ROOT

ENTRYPOINT ["sh", "./gradlew", "bootJar"]

CMD ["java", "Djava.security.egd=file:/dev/./urandom", "./build/libs/sample-0.0.1-SNAPSHOT.jar"]
