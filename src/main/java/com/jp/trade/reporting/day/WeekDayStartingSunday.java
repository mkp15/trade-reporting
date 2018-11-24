package com.jp.trade.reporting.day;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayStartingSunday implements WeekDay {

 @Override
    public boolean isWeekDay(LocalDate localDate) {
        return localDate.getDayOfWeek() != DayOfWeek.FRIDAY && localDate.getDayOfWeek() != DayOfWeek.SATURDAY;
    }
}
