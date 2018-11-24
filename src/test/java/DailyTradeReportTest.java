import com.jp.trade.reporting.enums.TradeType;
import com.jp.trade.reporting.service.DailyReport;
import com.jp.trade.reporting.service.Report;
import com.jp.trade.reporting.vo.ReportVo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class DailyTradeReportTest extends BaseTest{


    //Entity, Rank, TotalAmountIncoming, TotalAmountOutgoing, AdjustedSettlementDate
    @Test
    public void testReportVo(){
        Report report = new DailyReport(this.instructions);
        List<ReportVo> reportVos = report.generate();
        Assert.assertEquals(16, reportVos.size());
        List<ReportVo> incomingReportVos = reportVos.stream()
                .filter(i -> i.getDirection().equals(TradeType.SELL.getDesc()))
                .collect(Collectors.toList());
        ReportVo reportVo = incomingReportVos.get(0);
        Assert.assertEquals("CNN", reportVo.getEntityName());
        Assert.assertEquals(1, reportVo.getRank());
        Assert.assertEquals(new BigDecimal("22345.00"), reportVo.getTotalAmount());
        Assert.assertEquals(TradeType.SELL.getDesc(), reportVo.getDirection());
        Assert.assertEquals(LocalDate.of(2018,11,14), reportVo.getAdjustedSettlementDate());


        List<ReportVo> outgoingReportVos = reportVos.stream()
                .filter(i -> i.getDirection().equals(TradeType.BUY.getDesc()))
                .collect(Collectors.toList());
        reportVo = outgoingReportVos.get(0);
        Assert.assertEquals("EntityTwo", reportVo.getEntityName());
        Assert.assertEquals(1, reportVo.getRank());
        Assert.assertEquals(new BigDecimal("40100.000"), reportVo.getTotalAmount());
        Assert.assertEquals(TradeType.BUY.getDesc(), reportVo.getDirection());
        Assert.assertEquals(LocalDate.of(2018,11,13), reportVo.getAdjustedSettlementDate());
    }
}
