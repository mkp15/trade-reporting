package com.jp.trade.reporting.day;

import java.util.Currency;

public class WeekDayFactory {

    private WeekDayFactory(){

    }

    public static WeekDay getBusinessDay(Currency currency) {
        if(currency.getCurrencyCode().equals("AED") || currency.getCurrencyCode().equals("SAR")){
            return new WeekDayStartingSunday();
        }else{
            return new WeekDayStartingMonday();
        }
    }
}
