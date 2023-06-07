package com.newbee.alarm_lib.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/12 0012.
 *
 * 桃子时钟的具体信息
 */
public class TaoziTimeBean implements Serializable{
    private int week;//周几      0:"星期天",1:"星期一",2:"星期二",3:"星期三",4:"星期四",5:"星期五",6:"星期六"
    private int year;//年
    private int month;//月
    private int day;//日
    private int hour;//时
    private int minute;//分
    private int second;//秒
    private long time;//当前时间

    public TaoziTimeBean() {
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TaoziTimeBean{" +
                "week=" + week +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                ", time=" + time +
                '}';
    }
}
