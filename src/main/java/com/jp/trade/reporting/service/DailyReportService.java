package com.jp.trade.reporting.service;

import com.jp.trade.reporting.entity.Instruction;
import com.jp.trade.reporting.enums.TradeType;
import com.jp.trade.reporting.vo.RankVo;
import com.jp.trade.reporting.vo.ReportVo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DailyReportService implements Report {

    private final List<Instruction> instructions;

    public DailyReportService(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public List<ReportVo> generate() {
        List<ReportVo> reportVos = StatsGenerator.calculateDailyIncomingRank(instructions).stream()
                .map(it -> new ReportVo(it.getEntityName(), it.getRank(), it.getTotal(), TradeType.SELL.getDesc(), it.getAdjustedSettlementDate()))
                .collect(Collectors.toList());

        List<ReportVo> outgoingReports = StatsGenerator.calculateDailyOutgoingRank(instructions).stream()
                .map(it -> new ReportVo(it.getEntityName(), it.getRank(), it.getTotal(), TradeType.BUY.getDesc(), it.getAdjustedSettlementDate()))
                .collect(Collectors.toList());
        reportVos.addAll(outgoingReports);
        return reportVos;
    }
}
