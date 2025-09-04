package io.gugaglonti.flink.serialization;

import java.io.IOException;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.gugaglonti.flink.entities.BinanceTradeEvent;

public class BinanceTradeDeserializationSchema implements DeserializationSchema<BinanceTradeEvent> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public BinanceTradeEvent deserialize(byte[] message) throws IOException {
        return mapper.readValue(message, BinanceTradeEvent.class);
    }

    @Override
    public boolean isEndOfStream(BinanceTradeEvent nextElement) {
        return false;
    }

    @Override
    public TypeInformation<BinanceTradeEvent> getProducedType() {
        return TypeInformation.of(BinanceTradeEvent.class);
    }
}