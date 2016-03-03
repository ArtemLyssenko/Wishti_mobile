package com.prototype.wishti.helpers;

import com.prototype.wishti.WishtiApp;

import java.util.Calendar;


public class CalendarUtility {

    public enum OutputType{
        LONG,
        SHORT
    }

    public static String getNameOfMonth(int month){

        return WishtiApp.MONTHS[--month];
    }

    public static String getWeekday(int day,OutputType type){
        switch (type){
            case LONG:
                return getNameOfDayOfTheWeekLong(day);
            case SHORT:
                return getNameOfDayOfTheWeekShort(day);
            default:
                return null;
        }
    }

    private static String getNameOfDayOfTheWeekLong(int dayOfWeek){

        return WishtiApp.WEEKDAYS[--dayOfWeek];

    }

    private static String getNameOfDayOfTheWeekShort(int dayOfWeek){

        return WishtiApp.WEEKDAYS_SHORT[--dayOfWeek];

    }

}


