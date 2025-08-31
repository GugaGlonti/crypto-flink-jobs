
curl -X POST http://localhost:8081/jars/4cfd2971-71bf-47b2-a9ec-a347d29d86f2_crypto-flink-jobs-1.0-SNAPSHOT.jar/run \
  -H "Content-Type: application/json" \
  -d '{"entryClass":"io.gugaglonti.flink.App"}'
