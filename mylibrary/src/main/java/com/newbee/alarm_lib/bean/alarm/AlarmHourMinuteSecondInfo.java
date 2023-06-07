package com.newbee.alarm_lib.bean.alarm;

import java.io.Serializable;

public class AlarmHourMinuteSecondInfo implements Serializable {
    private int hour;//时
    private int minute;//分
    private int second;//秒

    public AlarmHourMinuteSecondInfo() {
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

    @Override
    public String toString() {
        return "AlarmHourMinuteSecondInfo{" +
                "hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
    }
}
