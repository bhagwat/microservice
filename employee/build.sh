#!/usr/bin/env bash
./gradlew clean build
cp build/libs/employee.jar docker
docker build -t bhagwatkumar/employee ./docker
