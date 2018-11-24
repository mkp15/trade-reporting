package com.jp.trade.reporting.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RankVo {
    private final DailyStatsVo dailyAggregateStatsVo;
    private final int rank;

    public RankVo(DailyStatsVo dailyAggregateStatsVo, int rank){
      this.dailyAggregateStatsVo = dailyAggregateStatsVo;
      this.rank = rank;
    }

    public String getEntityName(){
        return dailyAggregateStatsVo.getEntityName();
    }

    public BigDecimal getTotal(){
        return dailyAggregateStatsVo.getTotal();
    }

    public LocalDate getAdjustedSettlementDate(){
        return dailyAggregateStatsVo.getSettlementDate();
    }

    public DailyStatsVo getDailyAggregateStatsVo() {
        return dailyAggregateStatsVo;
    }

    public int getRank() {
        return rank;
    }
}