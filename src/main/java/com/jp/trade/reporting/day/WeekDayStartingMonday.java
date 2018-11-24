package com.jp.trade.reporting.day;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayStartingMonday implements WeekDay {

    @Override
    public boolean isWeekDay(LocalDate localDate) {
        return localDate.getDayOfWeek() != DayOfWeek.SATURDAY && localDate.getDayOfWeek() != DayOfWeek.SUNDAY;
    }
}
