#!/bin/bash
set -e

curl -X POST http://localhost:8081/jars/a3849228-53fd-4952-890e-46dc9a9ebef7_crypto-flink-jobs-1.0-SNAPSHOT.jar/run \
  -H "Content-Type: application/json" \
  -d '{"entryClass":"io.gugaglonti.flink.App"}'
