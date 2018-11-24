package com.jp.trade.reporting.day;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayStartingSunday implements WeekDay {

    private static WeekDayStartingSunday weekDayStartingSunday = new WeekDayStartingSunday();

 @Override
    public boolean isWeekDay(LocalDate localDate) {
        return localDate.getDayOfWeek() != DayOfWeek.FRIDAY && localDate.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    static WeekDay getInstance(){
        return weekDayStartingSunday;
    }
}
