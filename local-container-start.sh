#!/bin/bash

./back-service/mvnw clean install
docker rm -f hack-service
docker rmi -f slav9n4ik/hack-service
docker build -t slav9n4ik/hack-service .
docker run -dit -p 8080:8080 --name hack-service slav9n4ik/hack-service
docker logs -f hack-service