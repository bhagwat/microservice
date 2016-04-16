#!/usr/bin/env bash
grails clean
grails war
cp build/libs/library-0.1.war docker
docker build -t bhagwatkumar/library ./docker
