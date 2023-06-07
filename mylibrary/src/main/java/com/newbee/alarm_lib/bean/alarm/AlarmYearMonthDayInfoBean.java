package com.newbee.alarm_lib.bean.alarm;

import java.io.Serializable;

public class AlarmYearMonthDayInfoBean implements Serializable {
    private int year;
    private int month;
    private int day;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "AlarmYearMonthDayInfoBean{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
