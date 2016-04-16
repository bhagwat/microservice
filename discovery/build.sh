#!/usr/bin/env bash
./gradlew clean build
cp build/libs/discovery.jar docker
docker build -t bhagwatkumar/discovery ./docker
