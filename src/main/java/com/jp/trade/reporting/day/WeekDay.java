package com.jp.trade.reporting.day;

import java.time.LocalDate;

public interface WeekDay {
    boolean isWeekDay(LocalDate localDate);
    default LocalDate sameOrNextBusinessDay(LocalDate localDate) {
        LocalDate nextWorkingDay = localDate;
        while (!isWeekDay(nextWorkingDay)){
            nextWorkingDay = nextWorkingDay.plusDays(1);
        }
        return nextWorkingDay;
    }
}
