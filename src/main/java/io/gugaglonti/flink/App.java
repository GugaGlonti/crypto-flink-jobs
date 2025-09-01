package io.gugaglonti.flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        final KafkaSource<String> consumer = KafkaSource.<String>builder()
            .setBootstrapServers("localhost:9092")
            .setTopics("binance_trades")
            .setGroupId("crypto-flink")
            .setStartingOffsets(OffsetsInitializer.earliest())
            .setValueOnlyDeserializer(new SimpleStringSchema())
            .build();

        DataStream<String> stream = env.fromSource(consumer, WatermarkStrategy.noWatermarks(), "Kafka Source");

        stream.map((MapFunction<String, String>) value -> value.toUpperCase());

        stream.print();

        try {
            env.execute("Flink Job Test");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
