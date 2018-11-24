import com.jp.trade.reporting.CsvLoader;
import com.jp.trade.reporting.entity.Instruction;
import com.jp.trade.reporting.entity.Trade;
import com.jp.trade.reporting.enums.TradeType;
import org.junit.Assert;
import org.junit.Before;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BaseTest {

    List<Instruction> instructions = new ArrayList<>();

    @Before
    public void setUp() {
        CsvLoader csvLoader = new CsvLoader();
        instructions = csvLoader.getInstructions();
        loadSampleInstructions();
    }

    private void loadSampleInstructions() {
        // EntityOne
        instructions.add(new Instruction(
                "EntityOne",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.22"),
                new Trade(450, new BigDecimal("150.5"), Currency.getInstance("AED"), TradeType.SELL))
        );

        instructions.add(new Instruction(
                "EntityOne",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.22"),
                new Trade(700, new BigDecimal("150.5"), Currency.getInstance("AED"), TradeType.BUY))
        );


        instructions.add(new Instruction(
                "EntityOne",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.22"),
                new Trade(450, new BigDecimal("150.5"), Currency.getInstance("AED"), TradeType.BUY))
        );

        // EntityTwo
        instructions.add(new Instruction(
                "EntityTwo",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.5"),
                new Trade(200, new BigDecimal("100.25"), Currency.getInstance("SGD"), TradeType.BUY))
        );

        instructions.add(new Instruction(
                "EntityTwo",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.0"),
                new Trade(300, new BigDecimal("100.25"), Currency.getInstance("USD"), TradeType.BUY))
        );

        // EntityThree
        instructions.add(new Instruction(
                "EntityThree",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.5"),
                new Trade(200, new BigDecimal("100.25"), Currency.getInstance("SGD"), TradeType.SELL))
        );

        instructions.add(new Instruction(
                "EntityThree",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.0"),
                new Trade(300, new BigDecimal("100.25"), Currency.getInstance("USD"), TradeType.SELL))
        );

        // EntityThree
        instructions.add(new Instruction(
                "EntityThree",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.5"),
                new Trade(200, new BigDecimal("100.25"), Currency.getInstance("SGD"), TradeType.SELL))
        );

        instructions.add(new Instruction(
                "EntityThree",
                LocalDate.of(2018, 11, 12),
                LocalDate.of(2018, 11, 13),
                new BigDecimal("0.0"),
                new Trade(300, new BigDecimal("100.25"), Currency.getInstance("USD"), TradeType.SELL))
        );
    }
}
