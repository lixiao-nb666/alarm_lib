package com.newbee.alarm_lib.bean;

import com.newbee.alarm_lib.bean.alarm.AlarmYearMonthDayInfoBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 需要的时间必须要执行
 */
public class NeedTimeMustDoBean implements Serializable {
    private String bs;
    private int lever;
    private long startTime;//结束的时间，只计算到时分秒
    private long endTime;//开始的时间，只计算到时分秒
    private String doThingKey;//执行操作的键,2个键一样，执行后一个
    private String doThingVue;//执行操作的值
    private Set<Integer> weeks;//周几
    private List<AlarmYearMonthDayInfoBean> alarmYearMonthDayInfoBeanList;//开始的年月日

    public NeedTimeMustDoBean() {
        weeks = new HashSet<>();
        alarmYearMonthDayInfoBeanList = new ArrayList<>();
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public int getLever() {
        return lever;
    }

    public void setLever(int lever) {
        this.lever = lever;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getDoThingKey() {
        return doThingKey;
    }

    public void setDoThingKey(String doThingKey) {
        this.doThingKey = doThingKey;
    }

    public String getDoThingVue() {
        return doThingVue;
    }

    public void setDoThingVue(String doThingVue) {
        this.doThingVue = doThingVue;
    }

    public Set<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(Set<Integer> weeks) {
        this.weeks = weeks;
    }

    public void addWeek(int week) {
        if (weeks == null) {
            weeks = new HashSet<>();
        }
        weeks.add(week);
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

    @Override
    public String toString() {
        return "NeedTimeMustDoBean{" +
                "bs='" + bs + '\'' +
                ", lever=" + lever +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", doThingKey='" + doThingKey + '\'' +
                ", doThingVue='" + doThingVue + '\'' +
                ", weeks=" + weeks +
                ", alarmYearMonthDayInfoBeanList=" + alarmYearMonthDayInfoBeanList +
                '}';
    }
}
