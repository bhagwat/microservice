#!/usr/bin/env bash
./gradlew build
cd discovery
./build.sh
cd ../employee
./build.sh
cd ../library
./build.sh
cd ..
