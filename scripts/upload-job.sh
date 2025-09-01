#!/bin/bash
set -e

curl -X POST http://localhost:8081/jars/upload \
  -F "jarfile=@target/crypto-flink-jobs-1.0-SNAPSHOT.jar"
