package com.jp.trade.reporting.entity;

import com.jp.trade.reporting.enums.TradeType;

import java.math.BigDecimal;
import java.util.Currency;

public class Trade {
    private final int unit;
    private final BigDecimal pricePerUnit;
    private final Currency currency;
    private final TradeType tradeType;

    public Trade(int unit, BigDecimal pricePerUnit, Currency currency, TradeType tradeType){
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.currency = currency;
        this.tradeType = tradeType;
    }

     int getUnit() {
        return unit;
    }

     BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

     Currency getCurrency() {
        return currency;
    }

     TradeType getTradeType() {
        return tradeType;
    }
}
