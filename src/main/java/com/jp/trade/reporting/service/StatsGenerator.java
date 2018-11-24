package com.jp.trade.reporting.service;

import com.jp.trade.reporting.entity.Instruction;
import com.jp.trade.reporting.enums.TradeType;
import com.jp.trade.reporting.vo.DailyStatsVo;
import com.jp.trade.reporting.vo.RankVo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

public final class StatsGenerator {

    private static final Predicate<Instruction> BUY_TRADE_TYPE_FILTER = (i) -> i.getTradeType() == TradeType.BUY;

    private static final Predicate<Instruction> SELL_TRADE_TYPE_FILTER = (i) -> i.getTradeType() == TradeType.SELL;

    private StatsGenerator() {
        throw new UnsupportedOperationException("can't not be instantiated");
    }

    public static List<RankVo> calculateDailyOutgoingRank(final List<Instruction> instructions) {
        return getPrepareRanking(instructions, BUY_TRADE_TYPE_FILTER);
    }

    public static List<RankVo> calculateDailyIncomingRank(final List<Instruction> instructions) {
        return getPrepareRanking(instructions, SELL_TRADE_TYPE_FILTER);
    }

    private static List<RankVo> getPrepareRanking(List<Instruction> instructions, Predicate<Instruction> filter) {
        final List<DailyStatsVo> dailyStatsVos = getDailyStatsVos(instructions, filter);
        final List<RankVo> rankVos = new ArrayList<>();
        dailyStatsVos.stream().collect(groupingBy(DailyStatsVo::getSettlementDate))
                .forEach((date, dailyAggregateStats) -> {
                    final AtomicInteger rank = new AtomicInteger(1);
                    List<RankVo> ranks = dailyAggregateStats.stream()
                            .sorted((a, b) -> b.getTotal().compareTo(a.getTotal()))
                            .map(dailyAggregateStatsVo ->
                                    new RankVo(dailyAggregateStatsVo, rank.getAndIncrement())).collect(toList());
                    rankVos.addAll(ranks);
                });
        return rankVos;
    }

    private static List<DailyStatsVo> getDailyStatsVos(List<Instruction> instructions, Predicate<Instruction> sellTradeTypeFilter) {
        final List<DailyStatsVo> dailyStatsVos = new ArrayList<>();
        instructions.stream()
                .filter(sellTradeTypeFilter)
                .collect(groupingBy(Instruction::getEntityName))
                .forEach((entity, entityInstructions) -> {
                    entityInstructions.stream()
                            .collect(groupingBy((Instruction::getAdjustedSettlementDate)))
                            .forEach((date, instructionsByDate) -> {
                                BigDecimal total = instructionsByDate.stream()
                                        .map(Instruction::getAmountInUSD)
                                        .reduce(BigDecimal.ZERO, BigDecimal::add);
                                dailyStatsVos.add(new DailyStatsVo(entity, total, date));
                            });
                });
        return dailyStatsVos;
    }
}
