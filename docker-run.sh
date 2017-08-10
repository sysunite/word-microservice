#!/usr/bin bash

docker build -t sysunite/word-microservice:1.0.3 .
docker run --name word-microservice -p 9268:9268 -t sysunite/word-microservice:1.0.3
