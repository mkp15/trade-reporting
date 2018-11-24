package com.jp.trade.reporting.entity;

import com.jp.trade.reporting.day.WeekDay;
import com.jp.trade.reporting.day.WeekDayFactory;
import com.jp.trade.reporting.enums.TradeType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public final class Instruction {

    private final String entityName;
    private final LocalDate instructionDate;
    private final LocalDate settlementDate;
    private final BigDecimal fx;
    private final Trade trade;


    public Instruction(String entityName, LocalDate instructionDate, LocalDate settlementDate, BigDecimal fx, Trade trade) {
        this.entityName = entityName;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.fx = fx;
        this.trade = trade;
    }

    public String getEntityName() {
        return entityName;
    }


    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public BigDecimal getFx() {
        return fx;
    }

    public Currency getCurrency(){
        return this.trade.getCurrency();
    }

    public LocalDate getAdjustedSettlementDate(){
        WeekDay weekDay = WeekDayFactory.getBusinessDay(this.getCurrency());
        return weekDay.sameOrNext(this.settlementDate);
    }

    public TradeType getTradeType(){
        return this.trade.getTradeType();
    }

    public BigDecimal getAmountInUSD() {
        BigDecimal fx = this.trade.getCurrency().equals(Currency.getInstance("USD"))? BigDecimal.ONE: this.fx;
        return new BigDecimal(this.trade.getUnit())
                .multiply(this.trade.getPricePerUnit())
                .multiply(fx);
    }
}
