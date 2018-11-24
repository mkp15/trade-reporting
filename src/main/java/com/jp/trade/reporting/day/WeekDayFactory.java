package com.jp.trade.reporting.day;

import java.util.Currency;

public class WeekDayFactory {

    private WeekDayFactory(){

    }

    public static WeekDay getWeekDay(Currency currency) {
        if(currency.getCurrencyCode().equals("AED") || currency.getCurrencyCode().equals("SAR")){
            return WeekDayStartingSunday.getInstance();
        }else{
            return WeekDayStartingMonday.getInstance();
        }
    }
}
