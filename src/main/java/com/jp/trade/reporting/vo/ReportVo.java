package com.jp.trade.reporting.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ReportVo {

    private final String entityName;
    private final int rank;
    private final String direction;
    private final BigDecimal totalAmount;
    private final LocalDate adjustedSettlementDate;

    public ReportVo(String entityName, int rank, BigDecimal totalAmount, String direction, LocalDate adjustedSettlementDate){
      this.entityName = entityName;
      this.rank = rank;
      this.totalAmount = totalAmount;
      this.direction = direction;
      this.adjustedSettlementDate = adjustedSettlementDate;
    }

    public String getEntityName() {
        return entityName;
    }

    public int getRank() {
        return rank;
    }

    public String getDirection() {
        return direction;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString(){
        StringBuilder buildRecord = new StringBuilder();
        buildRecord.append(this.entityName);
        buildRecord.append("|");
        buildRecord.append(this.rank);
        buildRecord.append("|");
        buildRecord.append(this.totalAmount);
        buildRecord.append("|");
        buildRecord.append(this.direction);
        buildRecord.append("|");
        buildRecord.append(this.adjustedSettlementDate);
        return buildRecord.toString();
    }

    public LocalDate getAdjustedSettlementDate() {
        return adjustedSettlementDate;
    }
}
