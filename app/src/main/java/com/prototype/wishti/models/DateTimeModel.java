package com.prototype.wishti.models;


import com.prototype.wishti.helpers.CalendarUtility;

import java.util.Calendar;

public class DateTimeModel {

    private int month;
    private int date;
    private int year;
    private int daysInMonth;

    private String weekDay;
    private String nameOfMonth;

    private static DateTimeModel today;
    private Calendar calendar;


    public DateTimeModel(){
        init();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        today = null;
    }

    private void init(){

        if(this.calendar == null){
            this.calendar = Calendar.getInstance();
        }

        this.daysInMonth = this.calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.year = this.calendar.get(Calendar.YEAR);

        this.month = this.calendar.get(Calendar.MONTH);

        this.date = this.calendar.get(Calendar.DATE);

        this.weekDay = CalendarUtility.getWeekday(date, CalendarUtility.OutputType.SHORT);

        this.nameOfMonth = CalendarUtility.getNameOfMonth(month);

        if(today == null){
            today = this;
        }
    }

    @Override
    public String toString() {
        return this.getNameOfMonth();
    }

    public DateTimeModel getToday(){
        return today;
    }

    public int getDaysInMonth(){
        return this.daysInMonth;
    }

    public int getMonth() {
        return this.month;
    }

    public int getDate() {
        return this.date;
    }

    public String getWeekDay() {
        return this.weekDay;
    }

    public int getYear() {
        return this.year;
    }

    public String getNameOfMonth(){
        return this.nameOfMonth;
    }

    public void add(int days){
        this.calendar.add(Calendar.DATE,days);
        init();
    }

    public void set(int year, int month, int date){
        this.calendar.set(year, month, date);
    }

    public void set(int date){
        this.calendar.set(this.year, this.month, date);
    }

    public DateTimeModel setNextMonth(){
        this.set(this.year, this.month++, this.date);
        return this;
    }

    public DateTimeModel setNextMonth(int date){
        this.set(this.year,this.month++,date);
        return this;

    }

    public DateTimeModel setPreviousMonth(){
        this.set(this.year,this.month--,this.date);
        return this;
    }

    public DateTimeModel setPreviousMonth(int date){
        this.set(this.year,this.month--,date);
        return this;
    }

}
