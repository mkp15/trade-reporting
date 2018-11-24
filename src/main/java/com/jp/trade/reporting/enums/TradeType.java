package com.jp.trade.reporting.enums;

public enum TradeType {
    BUY("B", "Outgoing"), SELL("S", "Incoming");
    String code;
    String desc;
    TradeType(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static TradeType toTradeType(String value){
      for(TradeType tradeType :TradeType.values()){
          if(tradeType.code.equals(value)){
              return tradeType;
          }
      }
      throw new IllegalArgumentException("Value can not be converted TradeType");
    }

    public String getDesc() {
        return desc;
    }
}
