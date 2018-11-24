import com.jp.trade.reporting.service.StatsGenerator;
import com.jp.trade.reporting.vo.RankVo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class InstructionStatsTest extends BaseTest {



    //Create a report that shows
    // Ranking of entities based on incoming and outgoing amount. Eg: If entity foo instructs the highest
    // amount for a buy instruction, then foo is rank 1 for outgoing
    @Test
    public void testOutgoingRanking(){
        List<RankVo> rankVos = StatsGenerator.calculateDailyOutgoingRank(instructions);
        List<RankVo> rankBySettlementDate = rankVos.stream()
                .filter(it ->
                        it.getDailyAggregateStatsVo().getSettlementDate().equals(LocalDate.of(2018,11,13)))
                .collect(Collectors.toList());

        Assert.assertEquals(3,rankBySettlementDate.size());
        RankVo rankOne = rankBySettlementDate.get(0);
        Assert.assertEquals(1, rankOne.getRank());
        Assert.assertEquals("EntityTwo", rankOne.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("40100.000"), rankOne.getDailyAggregateStatsVo().getTotal());

        RankVo rankTwo = rankBySettlementDate.get(1);
        Assert.assertEquals(2, rankTwo.getRank());
        Assert.assertEquals("EntityOne", rankTwo.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("38076.500"), rankTwo.getDailyAggregateStatsVo().getTotal());

        RankVo rankTree = rankBySettlementDate.get(2);
        Assert.assertEquals(3, rankTree.getRank());
        Assert.assertEquals("CNN", rankTree.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("6172.50"), rankTree.getDailyAggregateStatsVo().getTotal());

       // settlement on 5th Nov

        rankBySettlementDate = rankVos.stream()
                .filter(it -> it.getDailyAggregateStatsVo().getSettlementDate().equals(LocalDate.of(2018,11,19)))
                .collect(Collectors.toList());

        Assert.assertEquals(2,rankBySettlementDate.size());

        rankOne = rankBySettlementDate.get(0);
        Assert.assertEquals(1, rankOne.getRank());
        Assert.assertEquals("CNN", rankOne.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("6172.50"), rankOne.getDailyAggregateStatsVo().getTotal());

        rankOne = rankBySettlementDate.get(1);
        Assert.assertEquals(2, rankOne.getRank());
        Assert.assertEquals("TVF", rankOne.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("3086.250"), rankOne.getDailyAggregateStatsVo().getTotal());
    }


    @Test
    public void testIncomingRanking(){
        List<RankVo> rankVos = StatsGenerator.calculateDailyIncomingRank(instructions);
        List<RankVo> rankBySettlementDate = rankVos.stream()
                .filter(it ->
                        it.getDailyAggregateStatsVo().getSettlementDate().equals(LocalDate.of(2018,11,13)))
                .collect(Collectors.toList());

        Assert.assertEquals(4,rankBySettlementDate.size());
        RankVo rankOne = rankBySettlementDate.get(0);
        Assert.assertEquals(1, rankOne.getRank());
        Assert.assertEquals("EntityThree", rankOne.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("80200.000"), rankOne.getDailyAggregateStatsVo().getTotal());

        RankVo rankTwo = rankBySettlementDate.get(1);
        Assert.assertEquals(2, rankTwo.getRank());
        Assert.assertEquals("EntityOne", rankTwo.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("14899.500"), rankTwo.getDailyAggregateStatsVo().getTotal());

        RankVo rankTree = rankBySettlementDate.get(2);
        Assert.assertEquals(3, rankTree.getRank());
        Assert.assertEquals("CNN", rankTree.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("5876.00"), rankTree.getDailyAggregateStatsVo().getTotal());

        RankVo rankFour = rankBySettlementDate.get(3);
        Assert.assertEquals(4, rankFour.getRank());
        Assert.assertEquals("TVF", rankFour.getDailyAggregateStatsVo().getEntityName());
        Assert.assertEquals(new BigDecimal("2938.000"), rankFour.getDailyAggregateStatsVo().getTotal());
    }
}
