package com.newbee.alarm_lib.bean.alarm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/7/15 0015.
 */

public class AlarmInfoBean implements Serializable {
    private String bs;//闹钟的标识
    private List<AlarmYearMonthDayInfoBean> alarmYearMonthDayInfoBeanList;
    private List<AlarmHourMinuteSecondInfo> alarmHourMinuteSecondInfoList;
    private Set<Integer> weeks;//周几
    private String alarmStr;


    public AlarmInfoBean() {
        alarmYearMonthDayInfoBeanList = new ArrayList<>();
        alarmHourMinuteSecondInfoList = new ArrayList<>();
        weeks = new HashSet<>();

    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public List<AlarmYearMonthDayInfoBean> getAlarmYearMonthDayInfoBeanList() {
        return alarmYearMonthDayInfoBeanList;
    }

    public void setAlarmYearMonthDayInfoBeanList(List<AlarmYearMonthDayInfoBean> alarmYearMonthDayInfoBeanList) {
        this.alarmYearMonthDayInfoBeanList = alarmYearMonthDayInfoBeanList;
    }

    public void addAlarmYearMonthDayInfoBean(AlarmYearMonthDayInfoBean alarmYearMonthDayInfoBean) {
        alarmYearMonthDayInfoBeanList.add(alarmYearMonthDayInfoBean);
    }

    public List<AlarmHourMinuteSecondInfo> getAlarmHourMinuteSecondInfoList() {
        return alarmHourMinuteSecondInfoList;
    }

    public void setAlarmHourMinuteSecondInfoList(List<AlarmHourMinuteSecondInfo> alarmHourMinuteSecondInfoList) {
        this.alarmHourMinuteSecondInfoList = alarmHourMinuteSecondInfoList;
    }

    public void addAlarmHourMinuteSecondInfo(AlarmHourMinuteSecondInfo alarmHourMinuteSecondInfo) {
        alarmHourMinuteSecondInfoList.add(alarmHourMinuteSecondInfo);
    }

    public Set<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(Set<Integer> weeks) {
        this.weeks = weeks;
    }

    public void addWeek(int week) {
        weeks.add(week);
    }



    public String getAlarmStr() {
        return alarmStr;
    }

    public void setAlarmStr(String alarmStr) {
        this.alarmStr = alarmStr;
    }


    @Override
    public String toString() {
        return "AlarmInfoBean{" +
                "bs='" + bs + '\'' +
                ", alarmYearMonthDayInfoBeanList=" + alarmYearMonthDayInfoBeanList +
                ", alarmHourMinuteSecondInfoList=" + alarmHourMinuteSecondInfoList +
                ", weeks=" + weeks +
                ", alarmStr='" + alarmStr + '\'' +
                '}';
    }
}
