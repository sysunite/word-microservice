#!/bin/bash
rm -rf lib
npm run prepublish
docker build -t sysunite/word-microservice:1.0.2 .