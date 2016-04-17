h1. Microservice

Contains sub projects required for managing microservices

h2. Config Server

A central place to manage external properties for applications across all environments

cd config
./gradlew build
java -Dserver.port=8888 -jar build/libs/config-0.1.SNAPSHOT.jar

h2. Eureka Server

Service registry for resilient mid-tier load balancing and failover.

cd discovery
./gradlew build
java -Dserver.port=1111 -jar build/libs/discovery.jar

h2. Zuul Server

Gateway service that provides dynamic routing, monitoring, resiliency, security 

cd zuul
./gradlew build
java -Dserver.port=8765 -jar build/libs/zull-0.1.SNAPSHOT.jar

h2. Employee Service

Basic Spring boot application using MongoDB as persistent layer and provides REST endpoints for managing Employee Entity.

cd employee
./gradlew build
java -Dserver.port=2222 -jar build/libs/employee.jar  # instance 1
java -Dserver.port=2223 -jar build/libs/employee.jar  # instance 2

h2. Library Service

Grails 3.1.4 application with rest-api profile using MySql as persistent layer and provides REST endpoints for managing Book Entity.

cd employee
grails war
java -Dserver.port=3333 -jar build/libs/library-0.1.war # instance 1
java -Dserver.port=3334 -jar build/libs/library-0.1.war # instance 2

h2. Test it

h2. Eureka Dashboard

http://localhost:1111

h2. Test employee and library services using Zull Server

curl -XGET http://localhost:8765/employee/employee.json  #Zull server
curl -XGET http://localhost:2222/employee.json  #Employee server

curl -XGET http://localhost:8765/library/book/list.json
curl -XGET http://localhost:3333/book/list.json

