package com.jp.trade.reporting.day;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayStartingMonday implements WeekDay {

    private static WeekDayStartingMonday weekDayStartingMonday = new WeekDayStartingMonday();

    @Override
    public boolean isWeekDay(LocalDate localDate) {
        return localDate.getDayOfWeek() != DayOfWeek.SATURDAY && localDate.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    static WeekDay getInstance(){
        return weekDayStartingMonday;
    }
}
