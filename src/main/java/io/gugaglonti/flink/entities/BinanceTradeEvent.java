package io.gugaglonti.flink.entities;

public class BinanceTradeEvent {
    public String eventType;
    public long eventTime;
    public String symbol;
    public long tradeId;
    public String price;
    public String quantity;
    public long tradeTime;
    public boolean isBuyermaker;
    public boolean ignore;

    public String getPrice() {
      return price;
    }
}
