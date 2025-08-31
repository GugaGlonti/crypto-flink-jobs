package io.gugaglonti.flink;

import java.util.List;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> text = env.fromData(List.of("Hello", "World"));

        text.map((MapFunction<String, String>) value -> value.toUpperCase());

        text.print();

        try {
            env.execute("Flink Job Test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
