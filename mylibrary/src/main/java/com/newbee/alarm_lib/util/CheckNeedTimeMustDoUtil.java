package com.newbee.alarm_lib.util;


import com.newbee.alarm_lib.bean.alarm.AlarmYearMonthDayInfoBean;
import com.newbee.alarm_lib.bean.NeedTimeMustDoBean;

public class CheckNeedTimeMustDoUtil {

    private static final long CAN_START_TIME_CHA = 60 * 60 * 1000;//闹钟能够启动的时间差距



    public static boolean checkYearAndMonthAndDayIsOk(NeedTimeMustDoBean needTimeMustDoBean, int year, int month, int day) {
        if(needTimeMustDoBean.getAlarmYearMonthDayInfoBeanList()==null||needTimeMustDoBean.getAlarmYearMonthDayInfoBeanList().size()==0){
            return false;
        }
        for(AlarmYearMonthDayInfoBean alarmYearMonthDayInfoBean:needTimeMustDoBean.getAlarmYearMonthDayInfoBeanList()){
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


    public static boolean checkWeekIsOk(NeedTimeMustDoBean needTimeMustDoBean,int week) {
        if(needTimeMustDoBean.getWeeks()==null||needTimeMustDoBean.getWeeks().size()==0){
            return false;
        }
        for (int w : needTimeMustDoBean.getWeeks()) {
            if (w == week) {
                return true;
            }
        }
        return false;
    }

    public static boolean nowHourCanStartTime(NeedTimeMustDoBean needTimeMustDoBean,int nowHour, int nowMinute, int nowSecond) {
        long nowTime = nowHour*60*60*1000+  nowMinute*60*1000 + nowSecond * 1000;
        if(nowTime>needTimeMustDoBean.getStartTime()&&nowTime<needTimeMustDoBean.getEndTime()){
            return true;
        }
        long cha = needTimeMustDoBean.getStartTime() - nowTime;
        if (cha <= CAN_START_TIME_CHA && cha >= 0) {
            return true;
        }
        return false;
    }


    //检测时间是否对得上 0无效，1开始，2结束
    public static int checkNeedTimeMustDo(NeedTimeMustDoBean needTimeMustDoBean,int nowHour, int nowMinute, int nowSecond) {
        long nowTime = nowHour*60*60*1000+  nowMinute*60*1000 + nowSecond * 1000;
        if(needTimeMustDoBean.getStartTime()>needTimeMustDoBean.getEndTime()||nowTime<needTimeMustDoBean.getStartTime()||nowTime>needTimeMustDoBean.getEndTime()){
           return 0;
        }
        if(needTimeMustDoBean.getEndTime()-nowTime>2000){
            return 1;
        }
        return 2;
    }






}
