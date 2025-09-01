#!/bin/bash
set -e

JAR_FILE_NAME=crypto-flink-jobs-1.0-SNAPSHOT.jar
JAR_FILE=target/$JAR_FILE_NAME

curl -X POST http://localhost:8081/jars/upload \
  -F "jarfile=@$JAR_FILE"
