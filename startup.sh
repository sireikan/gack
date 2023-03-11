#!/usr/bin/env bash
#
sh ./gradlew bootJar
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005 -Djava.security.egd=file:/dev/./urandom -jar ./build/libs/gack-0.0.1-SNAPSHOT.jar
