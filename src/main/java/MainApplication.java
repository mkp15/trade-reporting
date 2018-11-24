import com.jp.trade.reporting.CsvLoader;
import com.jp.trade.reporting.service.DailyReportService;
import com.jp.trade.reporting.service.Report;

public class MainApplication {
    public static void main(String[] args) {
        CsvLoader csvLoader = new CsvLoader();
        Report report = new DailyReportService(csvLoader.getInstructions());
        report.generate().forEach(System.out::println);
    }
}
