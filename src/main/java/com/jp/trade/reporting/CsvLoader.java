package com.jp.trade.reporting;

import com.jp.trade.reporting.entity.Instruction;
import com.jp.trade.reporting.entity.Trade;
import com.jp.trade.reporting.enums.TradeType;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CsvLoader {

    private List<Instruction> instructions = new ArrayList<>();

    public CsvLoader(){
        loadCSVInstructions();
    }

    private static String replaceDoubleQuote(String value){
        return value.replaceAll("\"","");
    }

    private void loadCSVInstructions(){
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
            Path insFilePath = Paths.get(this.getClass().getClassLoader().getResource("instructions.csv").toURI());
            List<String> lines = Files.readAllLines(insFilePath);
            String[] headerNames = lines.get(0).split(",");
            Map<String, Integer> columnHeaderIndex  = IntStream.range(0, headerNames.length)
                    .boxed()
                    .collect(Collectors.toMap(i -> replaceDoubleQuote(headerNames[i]), i -> i));

            lines.stream().skip(1).forEach( it -> {
                String[] values = it.split(",");
                String unit = values[columnHeaderIndex.get("Units")];
                String pricePerUnit = values[columnHeaderIndex.get("PerUnitPrice")];
                String instructionDate = values[columnHeaderIndex.get("InstructionDate")];
                String settlementDate = values[columnHeaderIndex.get("SettlementDate")];
                String currency = values[columnHeaderIndex.get("Currency")];
                String tradeType = values[columnHeaderIndex.get("TradeType")];
                String entity = values[columnHeaderIndex.get("Entity")];
                String fx = values[columnHeaderIndex.get("FX")];
                Trade trade = new Trade(Integer.valueOf(replaceDoubleQuote(unit)),
                        new BigDecimal(replaceDoubleQuote(pricePerUnit)),
                        Currency.getInstance(replaceDoubleQuote(currency)),
                        TradeType.toTradeType(replaceDoubleQuote(tradeType)));

                instructions.add(new Instruction(
                        replaceDoubleQuote(entity),
                        LocalDate.parse(replaceDoubleQuote(instructionDate), dateTimeFormatter),
                        LocalDate.parse(replaceDoubleQuote(settlementDate), dateTimeFormatter),
                        new BigDecimal(replaceDoubleQuote(fx)),trade)
                );

            });
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }
}
