package com.newbee.alarm_lib.util;


import com.newbee.alarm_lib.bean.alarm.AlarmHourMinuteSecondInfo;
import com.newbee.alarm_lib.bean.alarm.AlarmInfoBean;
import com.newbee.alarm_lib.bean.alarm.AlarmYearMonthDayInfoBean;

public class CheckAlarmUtil {

    public static final long ALARM_START_MINUTE_CHA=5;

    private static final long CAN_START_TIME_CHA = 1000*60*ALARM_START_MINUTE_CHA;//闹钟能够启动的时间差距



    public static boolean checkWeekIsOk(AlarmInfoBean alarmInfoBean,int week) {
        if(alarmInfoBean.getWeeks()==null||alarmInfoBean.getWeeks().size()==0){
            return false;
        }
        for (int w : alarmInfoBean.getWeeks()) {
            if (w == week) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkYearAndMonthAndDayIsOk(AlarmInfoBean alarmInfoBean,int year, int month, int day) {
        if(alarmInfoBean.getAlarmYearMonthDayInfoBeanList()==null||alarmInfoBean.getAlarmYearMonthDayInfoBeanList().size()==0){
            return false;
        }
        for(AlarmYearMonthDayInfoBean alarmYearMonthDayInfoBean:alarmInfoBean.getAlarmYearMonthDayInfoBeanList()){
            if(alarmYearMonthDayInfoBean.getYear()==0&&alarmYearMonthDayInfoBean.getMonth()==0&&alarmYearMonthDayInfoBean.getDay()==0)
                continue;
            if(alarmYearMonthDayInfoBean.getYear()!=0&&alarmYearMonthDayInfoBean.getYear()!=year)
                continue;
            if(alarmYearMonthDayInfoBean.getMonth()!=0&&alarmYearMonthDayInfoBean.getMonth()!=month)
                continue;
            if(alarmYearMonthDayInfoBean.getDay()!=0&&alarmYearMonthDayInfoBean.getDay()!=day)
                continue;
            return true;
        }
        return false;
    }


    public static boolean nowHourCanStartTime(AlarmInfoBean alarmInfoBean,int nowHour, int nowMinute, int nowSecond) {
        if(alarmInfoBean.getAlarmHourMinuteSecondInfoList()==null||alarmInfoBean.getAlarmHourMinuteSecondInfoList().size()==0){
            return false;
        }
        for(AlarmHourMinuteSecondInfo alarmHourMinuteSecondInfo:alarmInfoBean.getAlarmHourMinuteSecondInfoList()){
            long nowTime = nowHour*60*60*1000+  nowMinute*60*1000 + nowSecond * 1000;
            long alarmTime = alarmHourMinuteSecondInfo.getHour()*60*60*1000 + alarmHourMinuteSecondInfo.getMinute()*60*1000 + alarmHourMinuteSecondInfo.getSecond()* 1000;
            if(alarmTime>=nowTime){
                long cha = alarmTime - nowTime;
                if (cha <= CAN_START_TIME_CHA ) {
                    return true;
                }
            }

        }
        return false;
    }




    public static boolean checkNowHourIsOk(AlarmInfoBean alarmInfoBean,int nowHour, int nowMinute, int nowSecond) {
        if(alarmInfoBean.getAlarmHourMinuteSecondInfoList()==null||alarmInfoBean.getAlarmHourMinuteSecondInfoList().size()==0){
            return false;
        }
        for(AlarmHourMinuteSecondInfo alarmHourMinuteSecondInfo:alarmInfoBean.getAlarmHourMinuteSecondInfoList()){
            if ((alarmHourMinuteSecondInfo.getHour() == nowHour) && (alarmHourMinuteSecondInfo.getMinute() == nowMinute) && (alarmHourMinuteSecondInfo.getSecond()==nowSecond)) {
                return true;
            }
        }
        return false;
    }




}
