package com.jp.trade.reporting.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DailyStatsVo {
    private final LocalDate settlementDate;
    private final String entityName;
    private final BigDecimal total;

    public DailyStatsVo(String entityName, BigDecimal total, LocalDate settlementDate){
      this.entityName = entityName;
      this.total = total;
      this.settlementDate = settlementDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public String getEntityName() {
        return entityName;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
