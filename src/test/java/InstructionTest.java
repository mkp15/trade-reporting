import com.jp.trade.reporting.entity.Instruction;
import com.jp.trade.reporting.entity.Trade;
import com.jp.trade.reporting.enums.TradeType;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InstructionTest extends BaseTest {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    /*

   USD amount of a trade = Price per unit * Units * Agreed Fx

     */

    @Test
    public void testAmountInUSD() {
        Optional<BigDecimal> entityOneAmountInUSD = instructions
                .stream()
                .filter(i -> i.getEntityName().equals("EntityOne"))
                .map(Instruction::getAmountInUSD).findFirst();

        Assert.assertEquals(new BigDecimal("14899.500"), entityOneAmountInUSD.get());


        Optional<BigDecimal> entityTwoAmountInUSD = instructions
                .stream()
                .filter(i -> i.getEntityName().equals("EntityTwo"))
                .map(Instruction::getAmountInUSD).findFirst();

        Assert.assertEquals(new BigDecimal("10025.000"), entityTwoAmountInUSD.get());
    }

    @Test
    public void testAmountIfBaseCurrencyInUSD() {
        Optional<BigDecimal> entityOneAmountInUSD = instructions
                .stream()
                .filter(i -> i.getCurrency().getCurrencyCode().equals("USD"))
                .map(Instruction::getAmountInUSD).findFirst();

        Assert.assertEquals(new BigDecimal("32345.00"), entityOneAmountInUSD.get());
    }

    @Test
    public void testAdjustedSettlementDate() {
        // get example of weekend settlement date
        List<Instruction> filtered = instructions.stream()
                .filter(i -> i.getSettlementDate().equals(LocalDate.of(2018, 11, 16))
                        && i.getCurrency().equals(Currency.getInstance("AED"))
                )
                .collect(Collectors.toList());
        Assert.assertEquals(1, filtered.size());
        Assert.assertEquals("16/11/2018", dateTimeFormatter.format(filtered.get(0).getSettlementDate()));
        Assert.assertEquals("18/11/2018", dateTimeFormatter.format(filtered.get(0).getAdjustedSettlementDate()));

        // week end case
        filtered = instructions.stream()
                .filter(i -> i.getSettlementDate().equals(LocalDate.of(2018, 11, 4))
                        && i.getCurrency().equals(Currency.getInstance("EUR"))
                )
                .collect(Collectors.toList());
        Assert.assertEquals(1, filtered.size());
        Assert.assertEquals("4/11/2018", dateTimeFormatter.format(filtered.get(0).getSettlementDate()));
        Assert.assertEquals("5/11/2018", dateTimeFormatter.format(filtered.get(0).getAdjustedSettlementDate()));

        // weekday case
        filtered = instructions.stream()
                .filter(i -> i.getSettlementDate().equals(LocalDate.of(2018, 11, 2))
                        && i.getCurrency().equals(Currency.getInstance("EUR"))
                )
                .collect(Collectors.toList());
        Assert.assertEquals(1, filtered.size());
        Assert.assertEquals("2/11/2018", dateTimeFormatter.format(filtered.get(0).getSettlementDate()));
        Assert.assertEquals("2/11/2018", dateTimeFormatter.format(filtered.get(0).getAdjustedSettlementDate()));
    }
}
