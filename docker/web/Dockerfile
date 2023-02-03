FROM openjdk:19

RUN microdnf install findutils

ENV APP_ROOT /app

COPY .. $APP_ROOT
WORKDIR $APP_ROOT

ENTRYPOINT ["sh", "./gradlew", "bootRun"]
