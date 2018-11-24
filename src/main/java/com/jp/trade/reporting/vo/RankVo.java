package com.jp.trade.reporting.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RankVo {
    private final DailyStatsVo dailyStatsVo;
    private final int rank;

    public RankVo(DailyStatsVo dailyStatsVo, int rank){
      this.dailyStatsVo = dailyStatsVo;
      this.rank = rank;
    }

    public String getEntityName(){
        return dailyStatsVo.getEntityName();
    }

    public BigDecimal getTotal(){
        return dailyStatsVo.getTotal();
    }

    public LocalDate getAdjustedSettlementDate(){
        return dailyStatsVo.getSettlementDate();
    }

    public DailyStatsVo getDailyAggregateStatsVo() {
        return dailyStatsVo;
    }

    public int getRank() {
        return rank;
    }
}