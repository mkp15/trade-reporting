import com.jp.trade.reporting.day.WeekDay;
import com.jp.trade.reporting.day.WeekDayFactory;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Currency;

public class WeekDayTest {

    /*
    A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where
    the work week starts Sunday and ends Thursday. No other holidays to be taken into account.

    A trade can only be settled on a working day.

    If an instructed settlement date falls on a weekend, then the settlement date should be changed to
    the next working day

     */

    @Test
    public void testAEDWeekDay(){
        // start of week SUNDAY and ends THURSDAY
        WeekDay weekDay = WeekDayFactory.getBusinessDay(Currency.getInstance("AED"));
        Assert.assertFalse(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.SATURDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.SUNDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.MONDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.THURSDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.WEDNESDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.THURSDAY)));
        Assert.assertFalse(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.FRIDAY)));
    }

    @Test
    public void testSARWeekDay(){
        // start of week SUNDAY and ends THURSDAY
        WeekDay weekDay = WeekDayFactory.getBusinessDay(Currency.getInstance("SAR"));
        Assert.assertFalse(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.SATURDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.SUNDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.MONDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.THURSDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.WEDNESDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.THURSDAY)));
        Assert.assertFalse(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.FRIDAY)));
    }

    @Test
    public void testGeneralWeekDay(){
        // start of week SUNDAY and ends THURSDAY
        WeekDay weekDay = WeekDayFactory.getBusinessDay(Currency.getInstance("USD"));
        Assert.assertFalse(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.SATURDAY)));
        Assert.assertFalse(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.SUNDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.MONDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.THURSDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.WEDNESDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.THURSDAY)));
        Assert.assertTrue(weekDay.isWeekDay(LocalDate.now().with(DayOfWeek.FRIDAY)));
    }

    @Test
    public void testAEDSameOrNextBusinessDay(){
     // start of week MONDAY and ends FRIDAY
        LocalDate localDate = LocalDate.now();
        WeekDay weekDay = WeekDayFactory.getBusinessDay(Currency.getInstance("AED"));
        Assert.assertEquals(localDate.with(DayOfWeek.SUNDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.SATURDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.SUNDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.FRIDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.MONDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.MONDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.TUESDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.TUESDAY)));
    }

    @Test
    public void testSARSameOrNextBusinessDay(){
        // start of week MONDAY and ends FRIDAY
        LocalDate localDate = LocalDate.now();
        WeekDay weekDay = WeekDayFactory.getBusinessDay(Currency.getInstance("SAR"));
        Assert.assertEquals(localDate.with(DayOfWeek.SUNDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.SATURDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.SUNDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.FRIDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.MONDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.MONDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.TUESDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.TUESDAY)));
    }

    @Test
    public void testGeneralSameOrNextBusinessDay(){
        // start of week MONDAY and ends FRIDAY
        LocalDate localDate = LocalDate.now();
        WeekDay weekDay = WeekDayFactory.getBusinessDay(Currency.getInstance("USD"));
        Assert.assertEquals(localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)), weekDay.sameOrNext(localDate.with(DayOfWeek.SATURDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.FRIDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.FRIDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.MONDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.MONDAY)));
        Assert.assertEquals(localDate.with(DayOfWeek.TUESDAY), weekDay.sameOrNext(localDate.with(DayOfWeek.TUESDAY)));
        Assert.assertEquals(localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)), weekDay.sameOrNext(localDate.with(DayOfWeek.SUNDAY)));
    }
}
