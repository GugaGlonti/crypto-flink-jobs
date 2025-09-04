package io.gugaglonti.flink;

import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import io.gugaglonti.flink.entities.BinanceTradeEvent;
import io.gugaglonti.flink.serialization.BinanceTradeDeserializationSchema;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        final KafkaSource<BinanceTradeEvent> consumer = KafkaSource.<BinanceTradeEvent>builder()
            .setBootstrapServers("kafka-service.crypto.svc.cluster.local:9092")
            .setTopics("binance_trades")
            .setGroupId("crypto-flink")
            .setStartingOffsets(OffsetsInitializer.earliest())
            .setValueOnlyDeserializer(new BinanceTradeDeserializationSchema())
            .build();

        DataStream<BinanceTradeEvent> stream = env
            .fromSource(consumer, WatermarkStrategy.noWatermarks(), "Kafka Source");


        stream
            .map(BinanceTradeEvent::getPrice)
            .print();


        try {
            env.execute("Flink Job Test");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
